package com.cjw.reactivecommunityproject.web.system.resource_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementModifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysResourceManagementMapper {
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

    Integer insert(SysResourceManagementInsertEntity authRegisterVO);

    Integer update(SysResourceManagementModifyEntity sysResourceManagementModifyEntity);

    Integer delete(@Param("uid") Long uid);
}
