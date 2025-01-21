package com.cjw.reactivecommunityproject.web.system.role_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;

import java.util.List;

public interface SystemRoleManagementDao {

    List<SystemRoleManagementListEntity> selectList(PaginationVO pagination);
}
