package com.cjw.reactivecommunityproject.web.system.resource_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementModifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemResourceManagementMapper {
    List<SystemResourceManagementListEntity> selectList(PaginationVO paginationVO);

    SystemResourceManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            @Param("method") RcManageResourceMethodEnum method,
            @Param("urlPattern") String urlPattern
    );

    Boolean isOwner(
            @Param("uid") Long uid,
            @Param("userUid") String userUid
    );

    Integer insert(SystemResourceManagementInsertEntity authRegisterVO);

    Integer update(SystemResourceManagementModifyEntity systemResourceManagementModifyEntity);

    Integer delete(@Param("uid") Long uid);
}
