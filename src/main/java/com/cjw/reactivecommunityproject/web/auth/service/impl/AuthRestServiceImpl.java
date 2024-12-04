package com.cjw.reactivecommunityproject.web.auth.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import com.cjw.reactivecommunityproject.server.auth.service.AuthService;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.web.auth.dao.AuthRestDAO;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthRestErrorMessage;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthRestException;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRestServiceImpl implements AuthRestService {
    private final AuthRestDAO authRestDAO;

    private final JwtService jwtService;
    private final AuthService authService;
    private final CacheCustomService cacheCustomService;

    private final RcProperties rcProperties;
    private final PasswordEncoder passwordEncoder;

    private Integer getRoleUidByCommonEnvCode() {
        var envcode = cacheCustomService.getCommonCustomEnvCode("web.auth.service", "default.register.role.uid");
        if (envcode == null) {
            throw new AuthRestException(RcCommonErrorMessage.NOT_FOUND_ENV_CODE);
        }
        if(!NumberUtils.isDigits(envcode.getValue())){
            throw new AuthRestException(RcCommonErrorMessage.INVALID_ENV_CODE);
        }

        return NumberUtils.toInt(envcode.getValue());
    }

    @Override
    public RestResponseVO<Void> register(AuthRestRegisterVO authRestRegisterVO) {
        if (authRestDAO.isExistUserByEmail(authRestRegisterVO.email())) {
            throw new AuthRestException(AuthRestErrorMessage.EXIST_ADDED_EMAIL);
        }

        if (authRestDAO.isExistUserByNickname(authRestRegisterVO.nickname())) {
            throw new AuthRestException(AuthRestErrorMessage.EXIST_ADDED_NICKNAME);
        }

        var uid = String.valueOf(UUID.randomUUID());
        var password = passwordEncoder.encode(authRestRegisterVO.pw());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        String salt = BCrypt.hashpw(uid + calendar.getTime(), BCrypt.gensalt());

        authService.register(AuthRegisterVO.builder()
                .uid(uid)
                .roleUid(getRoleUidByCommonEnvCode())
                .email(authRestRegisterVO.email())
                .pw(password)
                .name(authRestRegisterVO.name())
                .nickname(authRestRegisterVO.nickname())
                .joinedRegion(rcProperties.config().defaultRegion())
                .build()
        , salt);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<AuthRestJwtTokenVO> login(AuthRestLoginVO authRestLoginVO) {
        var rcUserEntity = authRestDAO.selectRcUser(authRestLoginVO.email());

        if (rcUserEntity == null) {
            throw new AuthRestException(AuthRestErrorMessage.NOT_FOUND_EMAIL);
        }

        if (StringUtils.equalsIgnoreCase(rcUserEntity.enabled().name(), "N")) {
            throw new AuthRestException(AuthRestErrorMessage.NOT_FOUND_EMAIL);
        }

        if (!passwordEncoder.matches(authRestLoginVO.pw(), rcUserEntity.pw())) {
            throw new AuthRestException(AuthRestErrorMessage.INVALID_USER_PASSWORD);
        }

        switch (rcUserEntity.state().name().toUpperCase()) {
            case "LISTEN":
                throw new AuthRestException(AuthRestErrorMessage.LISTEN_JOINED_USER);
            case "REFUSE":
                throw new AuthRestException(AuthRestErrorMessage.REFUSE_JOINED_STATE);
            case "BEN":
                throw new AuthRestException(AuthRestErrorMessage.BEN_JOINED_STATE);
            case "WITHDRAW":
                throw new AuthRestException(AuthRestErrorMessage.WITHDRAW_JOINED_STATE);
        }

        var accessToken = jwtService.createAccessToken(SecurityAccessJwtVO.builder()
                .userUid(rcUserEntity.uid())
                .roleUid(rcUserEntity.roleUid())
                .build());
        var refreshToken = jwtService.createRefreshToken();

        var result = AuthRestJwtTokenVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return RestResponseVO.<AuthRestJwtTokenVO>builder()
                .result(true)
                .data(result)
                .build();
    }
}
