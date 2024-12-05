package com.cjw.reactivecommunityproject.web.auth.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestReissueJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRestRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtAccessTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthRestJwtTokenVO;

public interface AuthRestService {
    RestResponseVO<Void> register(AuthRestRegisterVO authRestRegisterVO);

    RestResponseVO<AuthRestJwtTokenVO> login(AuthRestLoginVO authRestLoginVO);

    RestResponseVO<AuthRestJwtAccessTokenVO> reissueByRefreshToken(AuthRestReissueJwtTokenVO authRestReissueJwtTokenVO);
}
