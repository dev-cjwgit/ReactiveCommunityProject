package com.cjw.reactivecommunityproject.web.system.role_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemRoleManagementMapper {
    List<SystemRoleManagementListEntity> selectList(PaginationVO paginationVO);

    SystemRoleManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Boolean isExistByName(@Param("name") String name);

    Integer insert(SystemRoleManagementInsertEntity systemRoleManagementInsertEntity);

    Boolean isExistByUid(@Param("uid") Integer uid);

    Boolean isMaxUidByRole();

    Boolean isOwner(@Param("uid") Integer uid, @Param("userUid") String userUid);

    Integer delete(Integer uid);

    Integer update(SystemRoleManagementModifyEntity systemRoleManagementModifyEntity);
}
