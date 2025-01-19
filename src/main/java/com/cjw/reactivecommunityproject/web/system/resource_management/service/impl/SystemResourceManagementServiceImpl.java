package com.cjw.reactivecommunityproject.web.system.resource_management.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.resource_management.dao.SystemResourceManagementDao;
import com.cjw.reactivecommunityproject.web.system.resource_management.exception.SystemResourceManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.resource_management.exception.SystemResourceManagementException;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementModifyEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.request.SystemResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.service.SystemResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemResourceManagementServiceImpl implements SystemResourceManagementService {
    private final SystemResourceManagementDao systemResourceManagementDao;

    private final PaginationService paginationService;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SystemResourceManagementListEntity>> readResourceMgmtList(SystemResourceManagementListVO systemResourceManagementListVO, PaginationRequestVO paginationRequestVO) {
        var list = systemResourceManagementDao.selectList(
                paginationService.createPagination(systemResourceManagementListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SystemResourceManagementListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }

    @Override
    public RestResponseVO<SystemResourceManagementDetailEntity> readDetail(Long uid) {
        var detail = systemResourceManagementDao.selectDetail(uid);
        if (detail == null) {
            throw new SystemResourceManagementException(SystemResourceManagementErrorMessage.NOT_FOUND_RESOURCE_DETAIL);
        }
        return RestResponseVO.<SystemResourceManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SystemResourceManagementCreateVO sysResourcemgmtCreateVO) {
        var isDuplicate = systemResourceManagementDao.isDuplicate(
                sysResourcemgmtCreateVO.method(),
                sysResourcemgmtCreateVO.urlPattern()
        );

        if (isDuplicate) {
            throw new SystemResourceManagementException(SystemResourceManagementErrorMessage.DUPLICATE_RESOURCE_INFO);
        }
        systemResourceManagementDao.insertTransactional(
                SystemResourceManagementInsertEntity.builder()
                        .method(sysResourcemgmtCreateVO.method())
                        .urlPattern(sysResourcemgmtCreateVO.urlPattern())
                        .description(sysResourcemgmtCreateVO.description())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }


    @Override
    public RestResponseVO<Void> modify(SystemResourceManagementModifyVO systemResourceManagementModifyVO) {
        var isExist = systemResourceManagementDao.isExistResourceByUid(systemResourceManagementModifyVO.uid());

        if (Boolean.FALSE.equals(isExist)) {
            throw new SystemResourceManagementException(SystemResourceManagementErrorMessage.NOT_FOUND_RESOURCE_DETAIL);
        }

        var isOwner = systemResourceManagementDao.isOwner(systemResourceManagementModifyVO.uid(), rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SystemResourceManagementException(RcCommonErrorMessage.UNAUTHORIZED_ACCESS);
        }

        systemResourceManagementDao.updateTransactional(
                SystemResourceManagementModifyEntity.builder()
                        .uid(systemResourceManagementModifyVO.uid())
                        .method(systemResourceManagementModifyVO.method())
                        .urlPattern(systemResourceManagementModifyVO.urlPattern())
                        .description(systemResourceManagementModifyVO.description())
                        .enabled(systemResourceManagementModifyVO.enabled())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }


    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isExist = systemResourceManagementDao.isExistResourceByUid(uid);

        if (Boolean.FALSE.equals(isExist)) {
            throw new SystemResourceManagementException(SystemResourceManagementErrorMessage.NOT_FOUND_RESOURCE);
        }

        var isOwner = systemResourceManagementDao.isOwner(uid, rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SystemResourceManagementException(RcCommonErrorMessage.UNAUTHORIZED_ACCESS);
        }

        systemResourceManagementDao.deleteTransactional(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
