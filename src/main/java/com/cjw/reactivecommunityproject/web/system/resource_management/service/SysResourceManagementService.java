package com.cjw.reactivecommunityproject.web.system.resource_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SysResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SysResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SysResourceManagementListVO;

import java.util.List;

public interface SysResourceManagementService {
    RestResponseVO<List<SysResourceManagementListEntity>> readResourceMgmtList(SysResourceManagementListVO sysResourceManagementListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SysResourceManagementDetailEntity> readDetail(Long uid);

    RestResponseVO<Void> create(SysResourceManagementCreateVO sysResourcemgmtCreateVO);

    RestResponseVO<Void> modify(SysResourceManagementModifyVO sysResourceManagementModifyVO);

    RestResponseVO<Void> remove(Long uid);

}
