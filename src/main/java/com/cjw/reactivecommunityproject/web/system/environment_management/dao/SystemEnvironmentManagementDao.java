package com.cjw.reactivecommunityproject.web.system.environment_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementModifyEntity;
import java.util.List;

public interface SystemEnvironmentManagementDao {

    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);

    SystemEnvironmentManagementDetailEntity selectDetail(String id);

    Boolean isExistEnvCodeById(String id);

    Boolean isCategoryAndOrderDuplicate(
            String category,
            Integer order
    );

    void insertTransactional(SystemEnvironmentManagementInsertEntity systemEnvironmentManagementInsertEntity);

    Boolean isOwner(String envId, String userUid);

    void deleteTransactional(String id);

    void updateTransactional(SystemEnvironmentManagementModifyEntity systemEnvironmentManagementModifyEntity);
}
