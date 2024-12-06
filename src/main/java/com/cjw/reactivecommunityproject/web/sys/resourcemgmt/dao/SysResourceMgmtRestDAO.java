package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.dao;

import com.cjw.reactivecommunityproject.common.spring.pagination.model.entity.PaginationVO;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity.SysResourceMgmtListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysResourceMgmtRestDAO {
    List<SysResourceMgmtListVO> selectList(PaginationVO paginationVO);
}
