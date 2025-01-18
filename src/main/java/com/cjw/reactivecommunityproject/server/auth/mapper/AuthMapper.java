package com.cjw.reactivecommunityproject.server.auth.mapper;

import com.cjw.reactivecommunityproject.server.auth.model.AuthRegisterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    Integer register(AuthRegisterVO authRegisterVO);
}
