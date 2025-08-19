package com.cjw.reactivecommunityproject.web.system.environment_management.service;

import com.cjw.reactivecommunityproject.common.spring.model.response.RestResponseVO;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationRequestVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementCreateVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementListVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.request.SystemEnvironmentManagementModifyVO;
import java.util.List;

public interface SystemEnvironmentManagementService {
    RestResponseVO<List<SystemEnvironmentManagementListEntity>> readEnvironmentManagementList(SystemEnvironmentManagementListVO systemResourceManagementListVO, PaginationRequestVO paginationRequestVO);

    RestResponseVO<SystemEnvironmentManagementDetailEntity> detail(String region, String id);

    RestResponseVO<Void> create(SystemEnvironmentManagementCreateVO systemEnvironmentManagementCreateVO);

    RestResponseVO<Void> remove(String region, String id);

    RestResponseVO<Void> modify(SystemEnvironmentManagementModifyVO systemEnvironmentManagementModifyVO);
}
