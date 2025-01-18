package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.mapper.SysResourceMgmtMapper;
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
    private final SysResourceMgmtMapper sysResourceMgmtMapper;

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void insert(SysResourceMgmtInsertVO sysResourceMgmtInsertVO) {
        var rtn = sysResourceMgmtMapper.insert(sysResourceMgmtInsertVO);
        log.info("SysResourceMgmtServiceImpl.insert : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void update(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO) {
        var rtn = sysResourceMgmtMapper.update(sysResourceMgmtUpdateVO);
        log.info("SysResourceMgmtServiceImpl.update : {}", rtn);
    }

    @Override
    @Transactional(transactionManager = "txManager", rollbackFor = Exception.class)
    public void delete(Long uid) {
        var rtn = sysResourceMgmtMapper.delete(uid);
        log.info("SysResourceMgmtServiceImpl.delete : {}", rtn);
    }
}
