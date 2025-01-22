package com.cjw.reactivecommunityproject.web.system.role_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemRoleManagementMapper {
    List<SystemRoleManagementListEntity> selectList(PaginationVO paginationVO);

    SystemRoleManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isDuplicateByName(@Param("name") String name);

    Integer insert(SystemRoleManagementInsertEntity systemRoleManagementInsertEntity);

    Boolean isDuplicateByUid(@Param("uid") Long uid);

    Boolean isMaxUidByRole();
}
