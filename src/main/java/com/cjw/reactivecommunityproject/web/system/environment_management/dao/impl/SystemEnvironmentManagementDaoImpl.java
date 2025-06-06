package com.cjw.reactivecommunityproject.web.system.environment_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.environment_management.dao.SystemEnvironmentManagementDao;
import com.cjw.reactivecommunityproject.web.system.environment_management.mapper.SystemEnvironmentManagementMapper;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.environment_management.model.entity.SystemEnvironmentManagementModifyEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SystemEnvironmentManagementDaoImpl implements SystemEnvironmentManagementDao {
    private final SystemEnvironmentManagementMapper systemEnvironmentManagementMapper;

    @Override
    public List<SystemEnvironmentManagementListEntity> selectList(PaginationVO pagination) {
        return systemEnvironmentManagementMapper.selectList(pagination);
    }

    @Override
    public SystemEnvironmentManagementDetailEntity selectDetail(String id) {
        return systemEnvironmentManagementMapper.selectDetail(id);
    }

    @Override
    public Boolean isExistEnvCodeById(String id) {
        return systemEnvironmentManagementMapper.isExistEnvCodeById(id);
    }

    @Override
    public Boolean isCategoryAndOrderDuplicate(String category, Integer order) {
        return systemEnvironmentManagementMapper.isCategoryAndOrderDuplicate(category, order, null);
    }

    @Override
    public Boolean isCategoryAndOrderDuplicate(String category, Integer order, String envId) {
        return systemEnvironmentManagementMapper.isCategoryAndOrderDuplicate(category, order, envId);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SystemEnvironmentManagementInsertEntity systemEnvironmentManagementInsertEntity) {
        var rtn = systemEnvironmentManagementMapper.insert(systemEnvironmentManagementInsertEntity);
        log.info("SystemEnvironmentManagementDaoImpl.insertTransactional() : {}", rtn);
    }

    @Override
    public Boolean isOwner(String envId, String userUid) {
        return systemEnvironmentManagementMapper.isOwner(envId, userUid);
    }

    @Override
    public void deleteTransactional(String id) {
        var rtn = systemEnvironmentManagementMapper.delete(id);
        log.info("SystemEnvironmentManagementDaoImpl.deleteTransactional() : {}", rtn);
    }

    @Override
    public void updateTransactional(SystemEnvironmentManagementModifyEntity systemEnvironmentManagementModifyEntity) {
        var rtn = systemEnvironmentManagementMapper.update(systemEnvironmentManagementModifyEntity);
        log.info("SystemEnvironmentManagementDaoImpl.updateTransactional() : {}", rtn);
    }
}
