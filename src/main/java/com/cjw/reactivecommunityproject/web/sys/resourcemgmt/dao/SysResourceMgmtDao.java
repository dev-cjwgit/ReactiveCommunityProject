package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtInsertVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtUpdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMgmtDao {
    void insertTransactional(SysResourceMgmtInsertVO sysResourceMgmtInsertVO);

    void updateTransactional(SysResourceMgmtUpdateVO sysResourceMgmtUpdateVO);

    void deleteTransactional(Long uid);

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
}
