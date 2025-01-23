package com.cjw.reactivecommunityproject.web.system.function_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;

import java.util.List;

public interface SystemFunctionManagementDao {

    List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination);
}
