package com.cjw.reactivecommunityproject.web.auth.service.impl;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.web.auth.dao.AuthDAO;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthErrorMessage;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthException;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.service.AuthRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthRestServiceImpl implements AuthRestService {
    private final AuthDAO authDAO;

    private final JwtService jwtService;


    @Override
    public RestResponseVO<Void> register(AuthRegisterVO authRegisterVO) {
        if (authDAO.isExistUserByEmail(authRegisterVO.email())) {
            throw new AuthException(AuthErrorMessage.EXIST_ADDED_EMAIL);
        }

        if (authDAO.isExistUserByNickname(authRegisterVO.nickname())) {
            throw new AuthException(AuthErrorMessage.EXIST_ADDED_NICKNAME);
        }


        return null;
    }

    @Override
    public RestResponseVO<AuthJwtTokenVO> login(AuthLoginVO authLoginVO) {
        var rcUserEntity = authDAO.selectRcUser(authLoginVO.email());

        if (rcUserEntity == null) {
            throw new AuthException(AuthErrorMessage.NOT_FOUND_EMAIL);
        }

        var accessToken = jwtService.createAccessToken(SecurityAccessJwtVO.builder()
                .userUid("qwer")
                .roleUid(10L)
                .build());
        var refreshToken = jwtService.createRefreshToken();

        var result = AuthJwtTokenVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return RestResponseVO.<AuthJwtTokenVO>builder()
                .result(true)
                .data(result)
                .build();
    }
}
