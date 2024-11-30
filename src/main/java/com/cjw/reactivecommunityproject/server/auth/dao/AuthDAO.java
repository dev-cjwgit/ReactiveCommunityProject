package com.cjw.reactivecommunityproject.server.auth.dao;

import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthDAO {
    Integer register(AuthRegisterVO authRegisterVO);
}
