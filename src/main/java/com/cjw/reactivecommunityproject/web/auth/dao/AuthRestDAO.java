package com.cjw.reactivecommunityproject.web.auth.dao;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthRestDAO {
    AuthRestRcUserVO selectRcUserByEmail(@Param("email") String email);

    AuthRestRcUserVO selectRcUserByUserUid(@Param("uid") String uid);


    Boolean isExistUserByEmail(@Param("email") String email);

    Boolean isExistUserByNickname(@Param("nickname") String nickname);
}
