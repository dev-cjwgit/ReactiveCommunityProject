package com.cjw.reactivecommunityproject.web.auth.dao;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRcUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthDAO {
    AuthRcUserVO selectRcUser(@Param("email") String email);

    Boolean isExistUserByEmail(@Param("email") String email);

    Boolean isExistUserByNickname(@Param("nickname") String nickname);
}
