package com.cjw.reactivecommunityproject.web.system.environment_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.mapper.SystemEnvironmentManagementMapper;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemEnvironmentManagementDaoImpl implements SystemEnvironmentManagementDao {
    private final SystemEnvironmentManagementMapper systemEnvironmentManagementMapper;

    @Override
    public List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination) {
        return systemEnvironmentManagementMapper.selectList(pagination);
    }
}
