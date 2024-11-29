package com.cjw.reactivecommunityproject.web.auth.service.impl;

import com.cjw.reactivecommunityproject.common.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.security.model.SecurityAccessJwtVO;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import com.cjw.reactivecommunityproject.web.auth.dao.AuthDAO;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthErrorMessage;
import com.cjw.reactivecommunityproject.web.auth.exception.AuthException;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
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
    public RestResponseVO<AuthJwtTokenVO> login(AuthLoginVO authLoginVO) {
        var rcUserEntity = authDAO.selectRcUser(authLoginVO.email());

        if (rcUserEntity == null) {
            throw new AuthException(AuthErrorMessage.NOT_FOUNT_RC_USER);
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
