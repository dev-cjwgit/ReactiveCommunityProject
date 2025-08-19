package com.cjw.reactivecommunityproject.web.system.environment_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemEnvironmentManagementMapper {
    List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination);

    SystemEnvironmentManagementDetailEntity selectDetail(@Param("region") String region, @Param("id") String id);

    Boolean isExistEnvCodeById(@Param("region") String region, @Param("id") String id);

    Boolean isCategoryAndOrderDuplicate(@Param("region") String region, @Param("category") String category, @Param("order") Integer order, @Param("id") String id);

    Integer insert(SystemEnvironmentManagementInsertEntity systemEnvironmentManagementInsertEntity);

    Boolean isOwner(
            @Param("region") String region
            , @Param("id") String envId
            , @Param("userUid") String userUid
    );

    Integer delete(@Param("region") String region, @Param("id") String id);

    Integer update(SystemEnvironmentManagementModifyEntity systemEnvironmentManagementModifyEntity);
}
