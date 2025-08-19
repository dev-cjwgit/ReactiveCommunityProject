package com.cjw.reactivecommunityproject.web.system.role_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.offset.model.request.PaginationOffsetVO;
import com.cjw.reactivecommunityproject.web.system.role_management.dao.SystemRoleManagementDao;
import com.cjw.reactivecommunityproject.web.system.role_management.mapper.SystemRoleManagementMapper;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.role_management.model.entity.SystemRoleManagementModifyEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SystemRoleManagementDaoImpl implements SystemRoleManagementDao {
    private final SystemRoleManagementMapper systemRoleManagementMapper;

    @Override
    public List<SystemRoleManagementListEntity> selectList(PaginationOffsetVO pagination) {
        return systemRoleManagementMapper.selectList(pagination);
    }

    @Override
    public SystemRoleManagementDetailEntity selectDetail(Long uid) {
        return systemRoleManagementMapper.selectDetail(uid);
    }

    @Override
    public Boolean isExistByName(String name) {
        return systemRoleManagementMapper.isExistByName(name, null);
    }

    @Override
    public Boolean isExistByName(String name, Integer uid) {
        return systemRoleManagementMapper.isExistByName(name, uid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SystemRoleManagementInsertEntity systemRoleManagementInsertEntity) {
        int rtn = systemRoleManagementMapper.insert(systemRoleManagementInsertEntity);
        log.info("systemRoleManagementMapper.insert() : {}", rtn);
    }

    @Override
    public Boolean isExistByUid(Integer uid) {
        return systemRoleManagementMapper.isExistByUid(uid);
    }

    @Override
    public Boolean isMaxUidByRole() {
        return systemRoleManagementMapper.isMaxUidByRole();
    }

    @Override
    public Boolean isOwner(Integer uid, String userUid) {
        return systemRoleManagementMapper.isOwner(uid, userUid);
    }

    @Override
    public void deleteTransactional(Integer uid) {
        int rtn = systemRoleManagementMapper.delete(uid);
        log.info("systemRoleManagementMapper.delete() : {}", rtn);
    }

    @Override
    public void updateTransactional(SystemRoleManagementModifyEntity systemRoleManagementModifyEntity) {
        int rtn = systemRoleManagementMapper.update(systemRoleManagementModifyEntity);
        log.info("systemRoleManagementMapper.update() : {}", rtn);
    }
}
