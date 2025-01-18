package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceManagementDao;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.exception.SysResourceManagementException;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementModifyEntity;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementCreateVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementModifyVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request.SysResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.service.SysResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceManagementServiceImpl implements SysResourceManagementService {
    private final SysResourceManagementDao sysResourceManagementDao;

    private final PaginationService paginationService;

    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SysResourceManagementListEntity>> readResourceMgmtList(SysResourceManagementListVO sysResourceManagementListVO, PaginationRequestVO paginationRequestVO) {
        var list = sysResourceManagementDao.selectList(
                paginationService.createPagination(sysResourceManagementListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SysResourceManagementListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }

    @Override
    public RestResponseVO<SysResourceManagementDetailEntity> readDetail(Long uid) {
        var detail = sysResourceManagementDao.selectDetail(uid);
        if (detail == null) {
            throw new SysResourceManagementException(SysResourceManagementErrorMessage.NOT_FOUND_RESOURCE_DETAIL);
        }
        return RestResponseVO.<SysResourceManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SysResourceManagementCreateVO sysResourcemgmtCreateVO) {
        var isDuplicate = sysResourceManagementDao.isDuplicate(
                sysResourcemgmtCreateVO.method(),
                sysResourcemgmtCreateVO.urlPattern()
        );

        if (isDuplicate) {
            throw new SysResourceManagementException(SysResourceManagementErrorMessage.DUPLICATE_RESOURCE_INFO);
        }
        sysResourceManagementDao.insertTransactional(
                SysResourceManagementInsertEntity.builder()
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
    public RestResponseVO<Void> modify(SysResourceManagementModifyVO sysResourceManagementModifyVO) {
        var isOwner = sysResourceManagementDao.isOwner(sysResourceManagementModifyVO.uid(), rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SysResourceManagementException(SysResourceManagementErrorMessage.UNAUTHORIZED_ACCESS);
        }

        sysResourceManagementDao.updateTransactional(
                SysResourceManagementModifyEntity.builder()
                        .uid(sysResourceManagementModifyVO.uid())
                        .method(sysResourceManagementModifyVO.method())
                        .urlPattern(sysResourceManagementModifyVO.urlPattern())
                        .description(sysResourceManagementModifyVO.description())
                        .enabled(sysResourceManagementModifyVO.enabled())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }


    @Override
    public RestResponseVO<Void> remove(Long uid) {
        var isOwner = sysResourceManagementDao.isOwner(uid, rcUserComponent.getUserUid());

        if (Boolean.FALSE.equals(isOwner)) {
            throw new SysResourceManagementException(SysResourceManagementErrorMessage.UNAUTHORIZED_ACCESS);
        }

        sysResourceManagementDao.deleteTransactional(uid);

        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
