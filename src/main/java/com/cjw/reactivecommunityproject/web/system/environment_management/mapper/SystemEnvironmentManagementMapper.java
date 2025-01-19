package com.cjw.reactivecommunityproject.web.system.environment_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemEnvironmentManagementMapper {
    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);

    SystemEnvironmentManagementDetailEntity selectDetail(@Param("id") String id);

    Boolean isIdDuplicate(@Param("id") String id);

    Boolean isCategoryAndOrderDuplicate(@Param("category") String category, @Param("order") Integer order);

    Integer insert(SystemEnvironmentManagementInsertEntity systemEnvironmentManagementInsertEntity);
}
