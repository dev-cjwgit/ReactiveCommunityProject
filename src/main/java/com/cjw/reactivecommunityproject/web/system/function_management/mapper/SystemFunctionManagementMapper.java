package com.cjw.reactivecommunityproject.web.system.function_management.mapper;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementModifyEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemFunctionManagementMapper {

    List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination);

    SystemFunctionManagementDetailEntity selectDetail(@Param("uid") Long uid);

    Integer insert(SystemFunctionManagementInsertEntity systemFunctionManagementInsertEntity);

    Integer update(SystemFunctionManagementModifyEntity systemFunctionManagementModifyEntity);

    Integer delete(Long uid);

    Boolean isExistByName(@Param("name") String name, @Param("uid") Long uid);

    Boolean isExistByUid(@Param("uid") Long uid);

    Boolean isOwner(@Param("uid") Long uid, @Param("userUid") String userUid);

}
