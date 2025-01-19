package com.cjw.reactivecommunityproject.web.system.environment_management.service.impl;

import com.cjw.reactivecommunityproject.common.spring.component.RcUserComponent;
import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.exception.SystemEnvironmentManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.environment_management.exception.SystemEnvironmentManagementException;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementListVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.service.SystemEnvironmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemEnvironmentManagementServiceImpl implements SystemEnvironmentManagementService {
    private final SystemEnvironmentManagementDao systemEnvironmentManagementDao;

    private final PaginationService paginationService;
    private final RcUserComponent rcUserComponent;

    @Override
    public RestResponseVO<List<SystemEnvironmentManagementListEntity>> readEnvironmentManagementList(SystemEnvironmentManagementListVO systemResourceManagementListVO, PaginationRequestVO paginationRequestVO) {
        var list = systemEnvironmentManagementDao.selectList(
                paginationService.createPagination(systemResourceManagementListVO, paginationRequestVO)
        );

        return RestResponseVO.<List<SystemEnvironmentManagementListEntity>>builder()
                .result(true)
                .data(list)
                .build();
    }

    @Override
    public RestResponseVO<SystemEnvironmentManagementDetailEntity> readDetail(String id) {
        var detail = systemEnvironmentManagementDao.selectDetail(id);
        if (detail == null) {
            throw new SystemEnvironmentManagementException(SystemEnvironmentManagementErrorMessage.NOT_FOUNT_ENV_CODE_DETAIL);
        }
        return RestResponseVO.<SystemEnvironmentManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }

    @Override
    public RestResponseVO<Void> create(SystemEnvironmentManagementCreateVO systemEnvironmentManagementCreateVO) {
        var isIdDuplicate = systemEnvironmentManagementDao.isIdDuplicate(systemEnvironmentManagementCreateVO.id());

        if (isIdDuplicate) {
            throw new SystemEnvironmentManagementException(SystemEnvironmentManagementErrorMessage.DUPLICATE_ENVCODE_INFO);
        }

        var category = systemEnvironmentManagementCreateVO.category();
        var order = systemEnvironmentManagementCreateVO.order();

        // 조건: 둘 다 null 이거나, 둘 다 값이 있어야 함
        if ((category == null) != (order == null)) {
            throw new SystemEnvironmentManagementException(SystemEnvironmentManagementErrorMessage.INVALID_CATEGORY_AND_ORDER_VALUE);
        }

        var isCategoryAndOrderDuplicate = systemEnvironmentManagementDao.isCategoryAndOrderDuplicate(
                systemEnvironmentManagementCreateVO.category(),
                systemEnvironmentManagementCreateVO.order()
        );

        if (isCategoryAndOrderDuplicate) {
            throw new SystemEnvironmentManagementException(SystemEnvironmentManagementErrorMessage.DUPLICATE_CATEGORY_AND_ORDER_INFO);
        }

        systemEnvironmentManagementDao.insertTransactional(
                SystemEnvironmentManagementInsertEntity.builder()
                        .id(systemEnvironmentManagementCreateVO.id())
                        .type(systemEnvironmentManagementCreateVO.type())
                        .value(systemEnvironmentManagementCreateVO.value())
                        .category(systemEnvironmentManagementCreateVO.category())
                        .order(systemEnvironmentManagementCreateVO.order())
                        .userUid(rcUserComponent.getUserUid())
                        .build()
        );
        return RestResponseVO.<Void>builder()
                .result(true)
                .build();
    }
}
