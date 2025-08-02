package com.cjw.reactivecommunityproject.web.auth.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwt;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.util.EnvCodeUtils;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.web.auth.dao.AuthDao;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthErrorMessage;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthException;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterEntity;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthEmailLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthReissueJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtAccessTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthService;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RedisTemplate<String, Object> redisTemplate;

    private final JwtService jwtService;
    private final AuthDao authDao;
    private final CacheCustomService cacheCustomService;

    private final RcProperties rcProperties;
    private final PasswordEncoder passwordEncoder;


    private Integer getRoleUidByCommonEnvCode() {
        var envcode = EnvCodeUtils.<Integer>convertEnvCodeByValue(cacheCustomService.getCustomCommonEnvCode("web.auth.service.default.register.role.uid"), Integer.class);
        if (envcode == null) {
            throw new AuthException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }

        return envcode;
    }

    @Override
    public RestResponseVO<Void> register(AuthRegisterVO authRegisterVO) {
        if (Boolean.TRUE.equals(authDao.isExistUserByEmail(authRegisterVO.email()))) {
            throw new AuthException(AuthErrorMessage.EXIST_ADDED_EMAIL);
        }

        if (Boolean.TRUE.equals(authDao.isExistUserByNickname(authRegisterVO.nickname()))) {
            throw new AuthException(AuthErrorMessage.EXIST_ADDED_NICKNAME);
        }

        if(Boolean.TRUE.equals(authDao.isExistUserByPhoneNumber(authRegisterVO.phoneNumber()))){
            throw new AuthException(AuthErrorMessage.EXIST_ADDED_PHONE_NUMBER);
        }

        var uid = String.valueOf(UUID.randomUUID());
        var password = passwordEncoder.encode(authRegisterVO.pw());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        String salt = BCrypt.hashpw(uid + calendar.getTime(), BCrypt.gensalt());

        authDao.registerTransactional(AuthRegisterEntity.builder()
                        .uid(uid)
                        .roleUid(getRoleUidByCommonEnvCode())
                        .phoneNumber(authRegisterVO.phoneNumber())
                        .email(authRegisterVO.email())
                        .pw(password)
                        .name(authRegisterVO.name())
                        .nickname(authRegisterVO.nickname())
                        .joinedRegion(rcProperties.config().defaultRegion())
                        .build()
                , salt);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<AuthRestJwtTokenVO> emailLogin(AuthEmailLoginVO authEmailLoginVO) {
        var rcUserEntity = authDao.selectRcUserByEmail(authEmailLoginVO.email());

        if (rcUserEntity == null) {
            throw new AuthException(AuthErrorMessage.NOT_FOUND_EMAIL);
        }

        if (Strings.CI.equals(rcUserEntity.enabled().name(), "N")) {
            throw new AuthException(AuthErrorMessage.NOT_FOUND_EMAIL);
        }

        if (!passwordEncoder.matches(authEmailLoginVO.pw(), rcUserEntity.pw())) {
            throw new AuthException(AuthErrorMessage.INVALID_USER_PASSWORD);
        }

        switch (rcUserEntity.state()) {
            case LISTEN:
                throw new AuthException(AuthErrorMessage.LISTEN_JOINED_USER);
            case REFUSE:
                throw new AuthException(AuthErrorMessage.REFUSE_JOINED_STATE);
            case BEN:
                throw new AuthException(AuthErrorMessage.BEN_JOINED_STATE);
            case WITHDRAW:
                throw new AuthException(AuthErrorMessage.WITHDRAW_JOINED_STATE);

            case ACCEPT:
                break;
        }

        // 중복 로그인 허용이면 하위 로직 무시
        if (!authEmailLoginVO.duplicationLogin()) {
            var userRefreshToken = String.valueOf(redisTemplate.opsForValue().get(rcUserEntity.uid() + ".refresh"));

            if (StringUtils.isNotBlank(userRefreshToken)) {
                throw new AuthException(AuthErrorMessage.ALREADY_LOGGED_IN_USER);
            }
        }

        var accessToken = jwtService.createAccessToken(SecurityAccessJwt.builder()
                .userUid(rcUserEntity.uid())
                .roleUid(rcUserEntity.roleUid())
                .build());
        var refreshToken = jwtService.createRefreshToken(rcUserEntity.uid());


        var result = AuthRestJwtTokenVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        authDao.loginTransactional(
                AuthLoginEntity.builder()
                        .userUid(rcUserEntity.uid())
                        .refreshToken(refreshToken)
                        .build()
        );

        return RestResponseVO.<AuthRestJwtTokenVO>builder()
                .result(true)
                .data(result)
                .build();
    }

    @Override
    public RestResponseVO<AuthRestJwtAccessTokenVO> reissueByRefreshToken(AuthReissueJwtTokenVO authReissueJwtTokenVO) {
        var claims = jwtService.getClaims(authReissueJwtTokenVO.refreshToken());
        if (claims == null) {
            throw new AuthException(AuthErrorMessage.INVALID_REFRESH_TOKEN);
        }

        String refreshToken = String.valueOf(redisTemplate.opsForValue().get(claims.getName() + ".refresh"));
        if (Strings.CI.equals(refreshToken, "null")) {
            throw new AuthException(AuthErrorMessage.NOT_LOGGED_IN_USER);
        }

        if (!StringUtils.equals(refreshToken, authReissueJwtTokenVO.refreshToken())) {
            throw new AuthException(AuthErrorMessage.NOT_MATCH_REFRESH_TOKEN);
        }

        var rcUserEntity = authDao.selectRcUserByUserUid(claims.userUid());

        if (rcUserEntity == null) {
            throw new AuthException(AuthErrorMessage.NOT_FOUND_USER);
        }

        String accessToken = jwtService.createAccessToken(
                SecurityAccessJwt.builder()
                        .userUid(claims.userUid())
                        .roleUid(rcUserEntity.roleUid())
                        .build()
        );
        var result = AuthRestJwtAccessTokenVO.builder()
                .accessToken(accessToken)
                .build();

        return RestResponseVO.<AuthRestJwtAccessTokenVO>builder()
                .result(true)
                .data(result)
                .build();
    }
}
