package com.cjw.reactivecommunityproject.web.system.role_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.role_management.dao.SystemRoleManagementDao;
import com.cjw.reactivecommunityproject.web.system.role_management.mapper.SystemRoleManagementMapper;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public SystemRoleManagementDetailEntity selectDetail(Long uid) {
        return systemRoleManagementMapper.selectDetail(uid);
    }

    @Override
    public Boolean isDuplicateByName(String name) {
        return systemRoleManagementMapper.isDuplicateByName(name);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SystemRoleManagementInsertEntity systemRoleManagementInsertEntity) {
        int rtn = systemRoleManagementMapper.insert(systemRoleManagementInsertEntity);
        log.info("systemRoleManagementMapper.insert() : {}", rtn);
    }

    @Override
    public Boolean isDuplicateByUid(Long uid) {
        return systemRoleManagementMapper.isDuplicateByUid(uid);
    }

    @Override
    public Boolean isMaxUidByRole() {
        return systemRoleManagementMapper.isMaxUidByRole();
    }
}
