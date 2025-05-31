package com.cjw.reactivecommunityproject.web.system.resource_management.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.system.resource_management.dao.SystemResourceManagementDao;
import com.cjw.reactivecommunityproject.web.system.resource_management.mapper.SystemResourceManagementMapper;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementDetailEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementInsertEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementListEntity;
import com.cjw.reactivecommunityproject.web.system.resource_management.model.entity.SystemResourceManagementModifyEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SystemResourceManagementDaoImpl implements SystemResourceManagementDao {
    private final SystemResourceManagementMapper systemResourceManagementMapper;

    @Override
    public List<SystemResourceManagementListEntity> selectList(PaginationVO paginationVO) {
        return systemResourceManagementMapper.selectList(paginationVO);
    }

    @Override
    public SystemResourceManagementDetailEntity selectDetail(Long uid) {
        return systemResourceManagementMapper.selectDetail(uid);
    }

    @Override
    public Boolean isDuplicateByMethodAndUrlPattern(RcManageResourceMethodEnum method, String urlPattern, Long uid) {
        return systemResourceManagementMapper.isDuplicateByMethodAndUrlPattern(method, urlPattern, uid);
    }

    @Override
    public Boolean isDuplicateByMethodAndUrlPattern(RcManageResourceMethodEnum method, String urlPattern) {
        return systemResourceManagementMapper.isDuplicateByMethodAndUrlPattern(method, urlPattern, null);
    }

    @Override
    public Boolean isOwner(Long uid, String userUid) {
        return systemResourceManagementMapper.isOwner(uid, userUid);
    }

    @Override
    public Boolean isExistResourceByUid(Long uid) {
        return systemResourceManagementMapper.isExistResourceByUid(uid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SystemResourceManagementInsertEntity systemResourceManagementInsertEntity) {
        var rtn = systemResourceManagementMapper.insert(systemResourceManagementInsertEntity);
        log.info("SysResourceMgmtServiceImpl.insert() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(SystemResourceManagementModifyEntity systemResourceManagementModifyEntity) {
        var rtn = systemResourceManagementMapper.update(systemResourceManagementModifyEntity);
        log.info("SysResourceMgmtServiceImpl.update() : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void deleteTransactional(Long uid) {
        var rtn = systemResourceManagementMapper.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete() : {}", rtn);
    }
}
