package com.cjw.reactivecommunityproject.web.auth.mapper;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRcUserEntity;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {
    AuthRcUserEntity selectRcUserByEmail(@Param("email") String email);

    AuthRcUserEntity selectRcUserByUserUid(@Param("uid") String uid);


    Boolean isExistUserByEmail(@Param("email") String email);

    Boolean isExistUserByNickname(@Param("nickname") String nickname);

    Integer register(AuthRegisterEntity authRegisterEntity);

}
