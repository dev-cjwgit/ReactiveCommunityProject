package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceMgmtReadListVO;

import java.util.List;

public interface SysResourceMgmtRestService {
    RestResponseVO<List<SysResourceMgmtListVO>> readResourceMgmtList(SysResourceMgmtReadListVO sysResourceMgmtReadListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SysResourceMgmtDetailVO> readDetail(Long uid);

    RestResponseVO<Void> create(SysResourceMgmtCreateVO sysResourcemgmtCreateVO);

    RestResponseVO<Void> modify(SysResourceMgmtModifyVO sysResourceMgmtModifyVO);

    RestResponseVO<Void> remove(Long uid);

}
