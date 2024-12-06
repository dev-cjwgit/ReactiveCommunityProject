package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtSelectListVO;

import java.util.List;

public interface SysResourceMgmtRestService {
    RestResponseVO<List<SysResourceMgmtListVO>> getResourceMgmtList(SysResourceMgmtSelectListVO sysResourceMgmtSelectListVO, PaginationRequestVO paginationRequestVO);
}
