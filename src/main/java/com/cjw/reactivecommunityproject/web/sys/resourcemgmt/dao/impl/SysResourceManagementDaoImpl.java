package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceManagementDao;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.mapper.SysResourceManagementRestMapper;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementInsertVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceManagementDaoImpl implements SysResourceManagementDao {
    private final SysResourceManagementRestMapper sysResourceManagementRestMapper;

    @Override
    public List<SysResourceManagementListVO> selectList(PaginationVO paginationVO) {
        return sysResourceManagementRestMapper.selectList(paginationVO);
    }

    @Override
    public SysResourceManagementDetailVO selectDetail(Long uid) {
        return sysResourceManagementRestMapper.selectDetail(uid);
    }

    @Override
    public Boolean isDuplicate(RcManageResourceMethodEnum method, String urlPattern) {
        return sysResourceManagementRestMapper.isDuplicate(method, urlPattern);
    }

    @Override
    public Boolean isOwner(Long uid, String userUid) {
        return sysResourceManagementRestMapper.isOwner(uid, userUid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SysResourceManagementInsertVO sysResourceManagementInsertVO) {
        var rtn = sysResourceManagementRestMapper.insert(sysResourceManagementInsertVO);
        log.info("SysResourceMgmtServiceImpl.insert : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(SysResourceManagementUpdateVO sysResourceManagementUpdateVO) {
        var rtn = sysResourceManagementRestMapper.update(sysResourceManagementUpdateVO);
        log.info("SysResourceMgmtServiceImpl.update : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void deleteTransactional(Long uid) {
        var rtn = sysResourceManagementRestMapper.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete : {}", rtn);
    }
}
