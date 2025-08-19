package com.cjw.reactivecommunityproject.web.system.function_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetRequestVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementModifyVO;
import java.util.List;

public interface SystemFunctionManagementService {
    RestResponseVO<List<SystemFunctionManagementListEntity>> readFunctionManagementList(SystemFunctionManagementListVO systemFunctionManagementListVO, PaginationOffsetRequestVO paginationOffsetRequestVO);

    RestResponseVO<SystemFunctionManagementDetailEntity> detail(Long uid);

    RestResponseVO<Void> create(SystemFunctionManagementCreateVO systemFunctionManagementCreateVO);

    RestResponseVO<Void> remove(Long uid);

    RestResponseVO<Void> modify(SystemFunctionManagementModifyVO systemFunctionManagementModifyVO);
}
