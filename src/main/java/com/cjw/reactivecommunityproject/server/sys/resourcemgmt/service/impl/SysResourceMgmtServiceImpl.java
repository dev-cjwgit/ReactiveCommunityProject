package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.impl;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.dao.SysResourceMgmtDAO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service.SysResourceMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysResourceMgmtServiceImpl implements SysResourceMgmtService {
    private final SysResourceMgmtDAO sysResourceMgmtDAO;

    @Override
    public void insertResourceMgmt(SysResourceMgmtInsertVO sysResourceMgmtInsertVO) {
        var rtn = sysResourceMgmtDAO.insertResourceMgmt(sysResourceMgmtInsertVO);
        log.info("SysResourceMgmtServiceImpl.insertResourceMgmt : {}", rtn);
    }
}
