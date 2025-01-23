package com.cjw.reactivecommunityproject.web.system.function_management.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.function_management.dao.SystemFunctionManagementDao;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;
import com.cjw.reactivecommunityproject.web.system.function_management.service.SystemFunctionManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemFunctionManagementServiceImpl implements SystemFunctionManagementService {
    private final SystemFunctionManagementDao systemFunctionManagementDao;

    private final PaginationService paginationService;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SystemFunctionManagementListEntity>> readFunctionManagementList(SystemFunctionManagementListVO systemFunctionManagementListVO, PaginationRequestVO paginationRequestVO) {
        var list = systemFunctionManagementDao.selectList(
                paginationService.createPagination(systemFunctionManagementListVO, paginationRequestVO)
        );
        return RestResponseVO.<List<SystemFunctionManagementListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }
}
