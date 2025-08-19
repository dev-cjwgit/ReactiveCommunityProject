package com.cjw.reactivecommunityproject.web.system.environment_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementModifyEntity;
import java.util.List;

public interface SystemEnvironmentManagementDao {

    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);

    SystemEnvironmentManagementDetailEntity selectDetail(String region, String id);

    Boolean isExistEnvCodeById(String region, String id);


    Boolean isCategoryAndOrderDuplicate(
            String region
            , String category
            , Integer order
    );

    Boolean isCategoryAndOrderDuplicate(
            String region
            , String category
            , Integer order
            , String envId
    );

    void insertTransactional(SystemEnvironmentManagementInsertEntity systemEnvironmentManagementInsertEntity);

    Boolean isOwner(String region, String envId, String userUid);

    void deleteTransactional(String region, String id);

    void updateTransactional(SystemEnvironmentManagementModifyEntity systemEnvironmentManagementModifyEntity);
}
