package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.dao.SysResourceMgmtDAO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtUpdateVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.SysResourceMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceMgmtServiceImpl implements SysResourceMgmtService {
    private final SysResourceMgmtDAO sysResourceMgmtDAO;

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insert(SysResourceMgmtInsertVO sysResourceMgmtInsertVO) {
        var rtn = sysResourceMgmtDAO.insert(sysResourceMgmtInsertVO);
        log.info("SysResourceMgmtServiceImpl.insert : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void update(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO) {
        var rtn = sysResourceMgmtDAO.update(sysResourceMgmtUpdateVO);
        log.info("SysResourceMgmtServiceImpl.update : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void delete(Long uid) {
        var rtn = sysResourceMgmtDAO.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete : {}", rtn);
    }
}
