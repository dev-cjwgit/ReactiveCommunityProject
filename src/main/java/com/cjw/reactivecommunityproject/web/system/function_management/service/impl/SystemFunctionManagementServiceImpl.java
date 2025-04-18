package com.cjw.reactivecommunityproject.web.system.function_management.service.impl;

import com.cjw.reactivecommunityproject.common.exception.model.RcCommonErrorMessage;
import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.function_management.dao.SystemFunctionManagementDao;
import com.cjw.reactivecommunityproject.web.system.function_management.exception.SystemFunctionManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.function_management.exception.SystemFunctionManagementException;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementModifyEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementListVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.request.SystemFunctionManagementModifyVO;
import com.cjw.reactivecommunityproject.web.system.function_management.service.SystemFunctionManagementService;
import com.cjw.reactivecommunityproject.web.system.resource_management.exception.SystemResourceManagementException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public RestResponseVO<SystemFunctionManagementDetailEntity> readDetail(Long uid) {
        var detail = systemFunctionManagementDao.selectDetail(uid);
        if (detail == null) {
            throw new SystemFunctionManagementException(SystemFunctionManagementErrorMessage.NOT_FOUND_FUNCTION_DETAIL);
        }

        return RestResponseVO.<SystemFunctionManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SystemFunctionManagementCreateVO systemFunctionManagementCreateVO) {
        var isDuplicateName = systemFunctionManagementDao.isExistByName(systemFunctionManagementCreateVO.name());
        if (isDuplicateName) {
            throw new SystemFunctionManagementException(SystemFunctionManagementErrorMessage.DUPLICATE_FUNCTION_NAME);
        }

        systemFunctionManagementDao.insertTransactional(
                SystemFunctionManagementInsertEntity.builder()
                        .type(systemFunctionManagementCreateVO.type())
                        .name(systemFunctionManagementCreateVO.name())
                        .description(systemFunctionManagementCreateVO.description())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );
        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isExistUid = systemFunctionManagementDao.isExistByUid(uid);
        if (Boolean.FALSE.equals(isExistUid)) {
            throw new SystemFunctionManagementException(SystemFunctionManagementErrorMessage.NOT_FOUND_FUNCTION);
        }

        var isOwner = systemFunctionManagementDao.isOwner(uid, rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SystemResourceManagementException(RcCommonErrorMessage.UNAUTHORIZED_ACCESS);
        }

        systemFunctionManagementDao.deleteTransactional(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }

    @Override
    public RestResponseVO<Void> modify(SystemFunctionManagementModifyVO systemFunctionManagementModifyVO) {
        var isExistUid = systemFunctionManagementDao.isExistByUid(systemFunctionManagementModifyVO.uid());
        if (Boolean.FALSE.equals(isExistUid)) {
            throw new SystemFunctionManagementException(SystemFunctionManagementErrorMessage.NOT_FOUND_FUNCTION);
        }

        var isOwner = systemFunctionManagementDao.isOwner(systemFunctionManagementModifyVO.uid(), rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SystemResourceManagementException(RcCommonErrorMessage.UNAUTHORIZED_ACCESS);
        }

        systemFunctionManagementDao.updateTransactional(
                SystemFunctionManagementModifyEntity.builder()
                        .uid(systemFunctionManagementModifyVO.uid())
                        .name(systemFunctionManagementModifyVO.name())
                        .type(systemFunctionManagementModifyVO.type())
                        .description(systemFunctionManagementModifyVO.description())
                        .enabled(systemFunctionManagementModifyVO.enabled())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
