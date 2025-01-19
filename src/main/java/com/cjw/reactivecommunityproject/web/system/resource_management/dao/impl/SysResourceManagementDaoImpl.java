package com.cjw.reactivecommunityproject.web.system.resource_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.dao.SysResourceManagementDao;
import com.cjw.reactivecommunityproject.web.system.resource_management.mapper.SysResourceManagementMapper;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SysResourceManagementModifyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceManagementDaoImpl implements SysResourceManagementDao {
    private final SysResourceManagementMapper sysResourceManagementMapper;

    @Override
    public List<SysResourceManagementListEntity> selectList(PaginationVO paginationVO) {
        return sysResourceManagementMapper.selectList(paginationVO);
    }

    @Override
    public SysResourceManagementDetailEntity selectDetail(Long uid) {
        return sysResourceManagementMapper.selectDetail(uid);
    }

    @Override
    public Boolean isDuplicate(RcManageResourceMethodEnum method, String urlPattern) {
        return sysResourceManagementMapper.isDuplicate(method, urlPattern);
    }

    @Override
    public Boolean isOwner(Long uid, String userUid) {
        return sysResourceManagementMapper.isOwner(uid, userUid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SysResourceManagementInsertEntity sysResourceManagementInsertEntity) {
        var rtn = sysResourceManagementMapper.insert(sysResourceManagementInsertEntity);
        log.info("SysResourceMgmtServiceImpl.insert() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(SysResourceManagementModifyEntity sysResourceManagementModifyEntity) {
        var rtn = sysResourceManagementMapper.update(sysResourceManagementModifyEntity);
        log.info("SysResourceMgmtServiceImpl.update() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void deleteTransactional(Long uid) {
        var rtn = sysResourceManagementMapper.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete() : {}", rtn);
    }
}
