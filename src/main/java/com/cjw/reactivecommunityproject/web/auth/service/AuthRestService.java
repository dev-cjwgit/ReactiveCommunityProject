package com.cjw.reactivecommunityproject.web.auth.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthJwtTokenVO;

public interface AuthRestService {
    RestResponseVO<Void> register(AuthRegisterVO authRegisterVO);

    RestResponseVO<AuthJwtTokenVO> login(AuthLoginVO authLoginVO);

}
