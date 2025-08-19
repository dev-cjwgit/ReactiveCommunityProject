package com.cjw.reactivecommunityproject.web.system.function_management.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementModifyEntity;
import java.util.List;

public interface SystemFunctionManagementDao {

    List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination);

    SystemFunctionManagementDetailEntity selectDetail(Long uid);

    void insertTransactional(SystemFunctionManagementInsertEntity systemFunctionManagementInsertEntity);

    void updateTransactional(SystemFunctionManagementModifyEntity systemFunctionManagementModifyEntity);

    Boolean isExistByName(String name);

    Boolean isExistByName(String name, Long uid);

    Boolean isExistByUid(Long uid);

    Boolean isOwner(Long uid, String userUid);

    void deleteTransactional(Long uid);
}
