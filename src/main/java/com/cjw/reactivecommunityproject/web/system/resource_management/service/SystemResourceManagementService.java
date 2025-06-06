package com.cjw.reactivecommunityproject.web.system.resource_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementModifyVO;
import java.util.List;

public interface SystemResourceManagementService {
    RestResponseVO<List<SystemResourceManagementListEntity>> readResourceMgmtList(SystemResourceManagementListVO systemResourceManagementListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SystemResourceManagementDetailEntity> detail(Long uid);

    RestResponseVO<Void> create(SystemResourceManagementCreateVO sysResourcemgmtCreateVO);

    RestResponseVO<Void> modify(SystemResourceManagementModifyVO systemResourceManagementModifyVO);

    RestResponseVO<Void> remove(Long uid);

}
