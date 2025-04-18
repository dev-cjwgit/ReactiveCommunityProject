package com.cjw.reactivecommunityproject.web.system.function_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.function_management.dao.SystemFunctionManagementDao;
import com.cjw.reactivecommunityproject.web.system.function_management.mapper.SystemFunctionManagementMapper;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.function_management.model.entity.SystemFunctionManagementModifyEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemFunctionManagementDaoImpl implements SystemFunctionManagementDao {
    private final SystemFunctionManagementMapper systemFunctionManagementMapper;

    @Override
    public List<SystemFunctionManagementListEntity> selectList(PaginationVO pagination) {
        return systemFunctionManagementMapper.selectList(pagination);
    }

    @Override
    public SystemFunctionManagementDetailEntity selectDetail(Long uid) {
        return systemFunctionManagementMapper.selectDetail(uid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SystemFunctionManagementInsertEntity systemFunctionManagementInsertEntity) {
        int rtn = systemFunctionManagementMapper.insert(systemFunctionManagementInsertEntity);
        log.info("systemFunctionManagementMapper.insert() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(SystemFunctionManagementModifyEntity systemFunctionManagementModifyEntity) {
        int rtn = systemFunctionManagementMapper.update(systemFunctionManagementModifyEntity);
        log.info("systemFunctionManagementMapper.update() : {}", rtn);
    }

    @Override
    public Boolean isExistByName(String name) {
        return systemFunctionManagementMapper.isExistByName(name);
    }

    @Override
    public Boolean isExistByUid(Long uid) {
        return systemFunctionManagementMapper.isExistByUid(uid);
    }

    @Override
    public Boolean isOwner(Long uid, String userUid) {
        return systemFunctionManagementMapper.isOwner(uid, userUid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void deleteTransactional(Long uid) {
        int rtn = systemFunctionManagementMapper.delete(uid);
        log.info("systemFunctionManagementMapper.delete() : {}", rtn);
    }
}
