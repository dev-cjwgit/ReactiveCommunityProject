package com.cjw.reactivecommunityproject.common.security.dao;

import com.cjw.reactivecommunityproject.common.security.model.entity.SecurityRoleResourceMapping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SecurityDAO {
    List<SecurityRoleResourceMapping> selectRoleResourceMapping(@Param("roleUid") Integer roleUid);
}
