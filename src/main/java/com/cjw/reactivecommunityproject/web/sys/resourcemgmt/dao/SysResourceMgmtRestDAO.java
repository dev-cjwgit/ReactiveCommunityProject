package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysResourceMgmtRestDAO {
    List<SysResourceMgmtListVO> selectList(PaginationVO paginationVO);

    SysResourceMgmtDetailVO selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            @Param("method") RcManageResourceMethodEnum method,
            @Param("url_pattern") String urlPattern
    );
}
