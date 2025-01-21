package com.cjw.reactivecommunityproject.web.system.role_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.dao.SystemRoleManagementDao;
import com.cjw.reactivecommunityproject.web.system.role_management.mapper.SystemRoleManagementMapper;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemRoleManagementDaoImpl implements SystemRoleManagementDao {
    private final SystemRoleManagementMapper systemRoleManagementMapper;

    @Override
    public List<SystemRoleManagementListEntity> selectList(PaginationVO pagination) {
        return systemRoleManagementMapper.selectList(pagination);
    }
}
