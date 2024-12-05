package com.cjw.reactivecommunityproject.server.auth.service;

import com.cjw.reactivecommunityproject.server.auth.model.AuthLoginVO;
import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;

public interface AuthService {
    void register(AuthRegisterVO authRegisterVO, String salt);

    void login(AuthLoginVO authLoginVO);
}
