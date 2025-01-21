package com.cjw.reactivecommunityproject.web.system.role_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemRoleManagementMapper {
    List<SystemRoleManagementListEntity> selectList(PaginationVO paginationVO);
}
