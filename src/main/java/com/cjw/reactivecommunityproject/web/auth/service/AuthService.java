package com.cjw.reactivecommunityproject.web.auth.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthReissueJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtAccessTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;

public interface AuthService {
    RestResponseVO<Void> register(AuthRegisterVO authRegisterVO);

    RestResponseVO<AuthRestJwtTokenVO> login(AuthLoginVO authLoginVO);

    RestResponseVO<AuthRestJwtAccessTokenVO> reissueByRefreshToken(AuthReissueJwtTokenVO authReissueJwtTokenVO);
}
