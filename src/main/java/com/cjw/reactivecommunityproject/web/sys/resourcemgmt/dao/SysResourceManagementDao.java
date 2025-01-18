package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementDetailVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementInsertVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementListVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceManagementUpdateVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceManagementDao {
    void insertTransactional(SysResourceManagementInsertVO sysResourceManagementInsertVO);

    void updateTransactional(SysResourceManagementUpdateVO sysResourceManagementUpdateVO);

    void deleteTransactional(Long uid);

    List<SysResourceManagementListVO> selectList(PaginationVO paginationVO);

    SysResourceManagementDetailVO selectDetail(@Param("uid") Long uid);

    Boolean isDuplicate(
            @Param("method") RcManageResourceMethodEnum method,
            @Param("urlPattern") String urlPattern
    );

    Boolean isOwner(
            @Param("uid") Long uid,
            @Param("userUid") String userUid
    );
}
