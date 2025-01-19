package com.cjw.reactivecommunityproject.web.system.environment_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemEnvironmentManagementMapper {
    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);

    SystemEnvironmentManagementDetailEntity selectDetail(String id);
}
