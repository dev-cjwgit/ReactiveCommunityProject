package com.cjw.reactivecommunityproject.web.system.role_management.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.role_management.dao.SystemRoleManagementDao;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementListVO;
import com.cjw.reactivecommunityproject.web.system.role_management.service.SystemRoleManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemRoleManagementServiceImpl implements SystemRoleManagementService {
    private final SystemRoleManagementDao systemRoleManagementDao;

    private final PaginationService paginationService;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SystemRoleManagementListEntity>> readRoleManagementList(SystemRoleManagementListVO systemRoleManagementListVO, PaginationRequestVO paginationRequestVO) {
        var list = systemRoleManagementDao.selectList(
                paginationService.createPagination(systemRoleManagementListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SystemRoleManagementListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }
}
