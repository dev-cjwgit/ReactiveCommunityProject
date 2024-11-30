package com.cjw.reactivecommunityproject.server.cache.dao;

import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheManageResourceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CacheDAO {
    List<CacheCommonRegionVO> selectCommonRegionList();

    List<CacheCommonEnvCodeVO> selectCommonEnvCodeList();

    List<CacheCommonLanguageCodeVO> selectCommonLanguageCodeList();

    List<CacheManageResourceVO> selectManageResourceList();

}
