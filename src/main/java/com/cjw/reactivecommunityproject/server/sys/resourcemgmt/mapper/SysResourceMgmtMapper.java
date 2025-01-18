package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.mapper;

import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model.SysResourceMgmtUpdateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysResourceMgmtMapper {
    Integer insert(SysResourceMgmtInsertVO authRegisterVO);

    Integer update(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO);

    Integer delete(@Param("uid") Long uid);
}
