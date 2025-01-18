package com.cjw.reactivecommunityproject.web.auth.dao;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthLoginVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;

public interface AuthDao {
    void registerTransactional(AuthRegisterVO authRegisterVO, String salt);

    void loginTransactional(AuthLoginVO authLoginVO);

    AuthRestRcUserVO selectRcUserByEmail(String email);

    AuthRestRcUserVO selectRcUserByUserUid(String uid);


    Boolean isExistUserByEmail(String email);

    Boolean isExistUserByNickname(String nickname);
}
