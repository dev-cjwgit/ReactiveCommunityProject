package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.dao;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysResourceMgmtDAO {
    Integer insertResourceMgmt(SysResourceMgmtInsertVO authRegisterVO);
}
