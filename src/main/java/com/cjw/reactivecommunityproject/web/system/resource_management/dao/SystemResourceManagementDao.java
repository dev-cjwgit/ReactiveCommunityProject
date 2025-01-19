package com.cjw.reactivecommunityproject.web.system.resource_management.dao;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementModifyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemResourceManagementDao {
    void insertTransactional(SystemResourceManagementInsertEntity systemResourceManagementInsertEntity);

    void updateTransactional(SystemResourceManagementModifyEntity systemResourceManagementModifyEntity);

    void deleteTransactional(Long uid);

    List<SystemResourceManagementListEntity> selectList(PaginationVO paginationVO);

    SystemResourceManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            RcManageResourceMethodEnum method,
            String urlPattern
    );

    Boolean isOwner(
            Long uid,
            String userUid
    );
}
