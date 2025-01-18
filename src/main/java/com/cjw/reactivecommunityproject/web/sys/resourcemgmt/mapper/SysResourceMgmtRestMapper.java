package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.mapper;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtUpdateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysResourceMgmtRestMapper {
    List<SysResourceMgmtListVO> selectList(PaginationVO paginationVO);

    SysResourceMgmtDetailVO selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            @Param("method") RcManageResourceMethodEnum method,
            @Param("urlPattern") String urlPattern
    );

    Boolean isOwner(
            @Param("uid") Long uid,
            @Param("userUid") String userUid
    );

    Integer insert(SysResourceMgmtInsertVO authRegisterVO);

    Integer update(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO);

    Integer delete(@Param("uid") Long uid);
}
