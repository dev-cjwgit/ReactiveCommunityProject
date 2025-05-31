package com.cjw.reactivecommunityproject.web.system.role_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementListVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementModifyVO;
import java.util.List;

public interface SystemRoleManagementService {
    RestResponseVO<List<SystemRoleManagementListEntity>> readRoleManagementList(SystemRoleManagementListVO build, PaginationRequestVO build1);

    RestResponseVO<SystemRoleManagementDetailEntity> detail(Long uid);

    RestResponseVO<Void> create(SystemRoleManagementCreateVO systemRoleManagementCreateVO);

    RestResponseVO<Void> remove(Integer uid);

    RestResponseVO<Void> modify(SystemRoleManagementModifyVO systemRoleManagementModifyVO);
}
