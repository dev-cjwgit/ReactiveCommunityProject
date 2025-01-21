package com.cjw.reactivecommunityproject.web.system.role_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementListVO;

import java.util.List;

public interface SystemRoleManagementService {
    RestResponseVO<List<SystemRoleManagementListEntity>> readRoleManagementList(SystemRoleManagementListVO build, PaginationRequestVO build1);
}
