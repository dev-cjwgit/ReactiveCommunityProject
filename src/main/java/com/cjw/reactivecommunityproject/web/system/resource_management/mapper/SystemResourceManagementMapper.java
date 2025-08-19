package com.cjw.reactivecommunityproject.web.system.resource_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemResourceManagementMapper {
    List<SystemResourceManagementListEntity> selectList(PaginationVO paginationVO);

    SystemResourceManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isDuplicateByMethodAndUrlPattern(
            @Param("method") RcManageResourceMethodEnum method
            , @Param("urlPattern") String urlPattern
            , @Param("uid") Long uid
    );

    Boolean isOwner(
            @Param("uid") Long uid
            , @Param("userUid") String userUid
    );

    Integer insert(SystemResourceManagementInsertEntity authRegisterVO);

    Integer update(SystemResourceManagementModifyEntity systemResourceManagementModifyEntity);

    Integer delete(@Param("uid") Long uid);

    Boolean isExistResourceByUid(@Param("uid") Long uid);
}
