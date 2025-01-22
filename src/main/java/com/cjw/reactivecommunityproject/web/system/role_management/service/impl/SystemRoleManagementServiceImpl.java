package com.cjw.reactivecommunityproject.web.system.role_management.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.resource_management.exception.SystemResourceManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.role_management.dao.SystemRoleManagementDao;
import com.cjw.reactivecommunityproject.web.system.role_management.exception.SystemRoleManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.role_management.exception.SystemRoleManagementException;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.request.SystemRoleManagementCreateVO;
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

    @Override
    public RestResponseVO<SystemRoleManagementDetailEntity> readDetail(Long uid) {
        var detail = systemRoleManagementDao.selectDetail(uid);
        if (detail == null) {
            throw new SystemRoleManagementException(SystemResourceManagementErrorMessage.NOT_FOUND_ROLE_DETAIL);
        }

        return RestResponseVO.<SystemRoleManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SystemRoleManagementCreateVO systemRoleManagementCreateVO) {
        var isDuplicateName = systemRoleManagementDao.isDuplicateByName(systemRoleManagementCreateVO.name());
        if (isDuplicateName) {
            throw new SystemRoleManagementException(SystemRoleManagementErrorMessage.DUPLICATE_RESOURCE_NAME);
        }

        var isDuplicateUid = systemRoleManagementDao.isDuplicateByUid(systemRoleManagementCreateVO.uid());
        if (isDuplicateUid) {
            throw new SystemRoleManagementException(SystemRoleManagementErrorMessage.DUPLICATE_RESOURCE_UID);
        }

        var isMaxUid = systemRoleManagementDao.isMaxUidByRole();
        if (isMaxUid){
            throw new SystemRoleManagementException(SystemRoleManagementErrorMessage.MAXIMUM_UID);
        }

        systemRoleManagementDao.insertTransactional(
                SystemRoleManagementInsertEntity.builder()
                        .uid(systemRoleManagementCreateVO.uid())
                        .name(systemRoleManagementCreateVO.name())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );
        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
