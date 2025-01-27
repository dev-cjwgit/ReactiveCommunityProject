package com.cjw.reactivecommunityproject.web.system.function_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;

import java.util.List;

public interface SystemFunctionManagementService {
    RestResponseVO<List<SystemFunctionManagementListEntity>> readFunctionManagementList(SystemFunctionManagementListVO systemFunctionManagementListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SystemFunctionManagementDetailEntity> readDetail(Long uid);
}
