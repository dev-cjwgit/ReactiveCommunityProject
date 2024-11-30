package com.cjw.reactivecommunityproject.server.cache.dao;

import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonRegionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CacheDAO {
    List<CacheCommonRegionVO> selectCommonRegionList();
}
