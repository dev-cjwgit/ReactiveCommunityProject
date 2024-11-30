package com.cjw.reactivecommunityproject.web.auth.dao;

import com.cjw.reactivecommunityproject.web.auth.model.entity.AuthRestRcUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthRestDAO {
    AuthRestRcUserVO selectRcUser(@Param("email") String email);

    Boolean isExistUserByEmail(@Param("email") String email);

    Boolean isExistUserByNickname(@Param("nickname") String nickname);
}
