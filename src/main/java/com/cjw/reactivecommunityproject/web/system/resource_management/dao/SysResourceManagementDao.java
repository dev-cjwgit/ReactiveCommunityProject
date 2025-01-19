package com.cjw.reactivecommunityproject.web.system.resource_management.dao;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementModifyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceManagementDao {
    void insertTransactional(SysResourceManagementInsertEntity sysResourceManagementInsertEntity);

    void updateTransactional(SysResourceManagementModifyEntity sysResourceManagementModifyEntity);

    void deleteTransactional(Long uid);

    List<SysResourceManagementListEntity> selectList(PaginationVO paginationVO);

    SysResourceManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            @Param("method") RcManageResourceMethodEnum method,
            @Param("urlPattern") String urlPattern
    );

    Boolean isOwner(
            @Param("uid") Long uid,
            @Param("userUid") String userUid
    );
}
