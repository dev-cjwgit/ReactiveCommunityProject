package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.service;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtUpdateVO;

public interface SysResourceMgmtService {
    void insert(SysResourceMgmtInsertVO sysResourceMgmtInsertVO);

    void update(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO);

    void delete(Long uid);

}
