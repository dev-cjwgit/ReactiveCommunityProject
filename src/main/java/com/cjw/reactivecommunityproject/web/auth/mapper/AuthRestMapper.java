package com.cjw.reactivecommunityproject.web.auth.mapper;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRegisterVO;
import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthRestMapper {
    AuthRestRcUserVO selectRcUserByEmail(@Param("email") String email);

    AuthRestRcUserVO selectRcUserByUserUid(@Param("uid") String uid);


    Boolean isExistUserByEmail(@Param("email") String email);

    Boolean isExistUserByNickname(@Param("nickname") String nickname);

    Integer register(AuthRegisterVO authRegisterVO);

}
