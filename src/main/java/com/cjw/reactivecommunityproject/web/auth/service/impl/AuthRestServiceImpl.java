package com.cjw.reactivecommunityproject.web.auth.service.impl;

import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import com.cjw.reactivecommunityproject.server.auth.service.AuthService;
import com.cjw.reactivecommunityproject.web.auth.dao.AuthRestDAO;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthRestErrorMessage;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthRestException;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final PasswordEncoder passwordEncoder;

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
        // TODO: Salt 반영
        String salt = BCrypt.hashpw(uid + calendar.getTime(), BCrypt.gensalt());

        authService.register(AuthRegisterVO.builder()
                .uid(uid)
                // TODO: 환경 코드로 변경
                .roleUid(10L)
                .email(authRestRegisterVO.email())
                .pw(password)
                .name(authRestRegisterVO.name())
                .nickname(authRestRegisterVO.nickname())
                // TODO: Properties 변경
                .joinedRegion("KOR")
                .build());

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

        var accessToken = jwtService.createAccessToken(SecurityAccessJwtVO.builder()
                .userUid("qwer")
                .roleUid(10L)
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
