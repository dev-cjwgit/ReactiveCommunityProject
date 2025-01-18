package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.impl;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao.SysResourceMgmtDao;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.mapper.SysResourceMgmtRestMapper;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtUpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceMgmtDaoImpl implements SysResourceMgmtDao {
    private final SysResourceMgmtRestMapper sysResourceMgmtRestMapper;

    @Override
    public List<SysResourceMgmtListVO> selectList(PaginationVO paginationVO) {
        return sysResourceMgmtRestMapper.selectList(paginationVO);
    }

    @Override
    public SysResourceMgmtDetailVO selectDetail(Long uid) {
        return sysResourceMgmtRestMapper.selectDetail(uid);
    }

    @Override
    public Boolean isDuplicate(RcManageResourceMethodEnum method, String urlPattern) {
        return sysResourceMgmtRestMapper.isDuplicate(method, urlPattern);
    }

    @Override
    public Boolean isOwner(Long uid, String userUid) {
        return sysResourceMgmtRestMapper.isOwner(uid, userUid);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insertTransactional(SysResourceMgmtInsertVO sysResourceMgmtInsertVO) {
        var rtn = sysResourceMgmtRestMapper.insert(sysResourceMgmtInsertVO);
        log.info("SysResourceMgmtServiceImpl.insert : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void updateTransactional(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO) {
        var rtn = sysResourceMgmtRestMapper.update(sysResourceMgmtUpdateVO);
        log.info("SysResourceMgmtServiceImpl.update : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void deleteTransactional(Long uid) {
        var rtn = sysResourceMgmtRestMapper.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete : {}", rtn);
    }
}
