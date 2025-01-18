package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementListVO;

import java.util.List;

public interface SysResourceManagementService {
    RestResponseVO<List<com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementListVO>> readResourceMgmtList(SysResourceManagementListVO sysResourceManagementListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SysResourceManagementDetailVO> readDetail(Long uid);

    RestResponseVO<Void> create(SysResourceManagementCreateVO sysResourcemgmtCreateVO);

    RestResponseVO<Void> modify(SysResourceManagementModifyVO sysResourceManagementModifyVO);

    RestResponseVO<Void> remove(Long uid);

}
