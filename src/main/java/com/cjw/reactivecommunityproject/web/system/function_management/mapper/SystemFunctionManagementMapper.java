package com.cjw.reactivecommunityproject.web.system.function_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemFunctionManagementMapper {

    List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination);
}
