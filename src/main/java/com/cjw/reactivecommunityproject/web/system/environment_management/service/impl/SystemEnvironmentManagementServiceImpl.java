package com.cjw.reactivecommunityproject.web.system.environment_management.service.impl;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.service.PaginationService;
import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.exception.SystemEnvironmentManagementErrorMessage;
import com.cjw.reactivecommunityproject.web.system.environment_management.exception.SystemEnvironmentManagementException;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
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
        if(detail == null){
            throw new SystemEnvironmentManagementException(SystemEnvironmentManagementErrorMessage.NOT_FOUNT_ENV_CODE_DETAIL);
        }
        return RestResponseVO.<SystemEnvironmentManagementDetailEntity>builder()
                .result(true)
                .data(detail)
                .build();
    }
}
