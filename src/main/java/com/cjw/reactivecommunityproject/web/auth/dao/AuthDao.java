package com.cjw.reactivecommunityproject.web.auth.dao;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRcUserEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterEntity;

public interface AuthDao {
    void registerTransactional(AuthRegisterEntity authRegisterEntity, String salt);

    void loginTransactional(AuthLoginEntity authLoginEntity);

    AuthRcUserEntity selectRcUserByEmail(String email);

    AuthRcUserEntity selectRcUserByUserUid(String uid);


    Boolean isExistUserByEmail(String email);

    Boolean isExistUserByNickname(String nickname);
}
