package com.cjw.reactivecommunityproject.web.auth.service;

import com.cjw.reactivecommunityproject.common.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.web.auth.model.response.AuthJwtTokenVO;
import com.cjw.reactivecommunityproject.web.auth.model.request.AuthLoginVO;

public interface AuthRestService {
    RestResponseVO<AuthJwtTokenVO> login(AuthLoginVO authLoginVO);
}
