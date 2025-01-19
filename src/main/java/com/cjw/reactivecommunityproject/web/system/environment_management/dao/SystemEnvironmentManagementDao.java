package com.cjw.reactivecommunityproject.web.system.environment_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;

import java.util.List;

public interface SystemEnvironmentManagementDao {

    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);
}
