package com.cjw.reactivecommunityproject.web.system.function_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.dao.SystemFunctionManagementDao;
import com.cjw.reactivecommunityproject.web.system.function_management.mapper.SystemFunctionManagementMapper;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemFunctionManagementDaoImpl implements SystemFunctionManagementDao {
    private final SystemFunctionManagementMapper systemFunctionManagementMapper;

    @Override
    public List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination) {
        return systemFunctionManagementMapper.selectList(pagination);
    }
}
