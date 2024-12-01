package com.cjw.reactivecommunityproject.server.cache.data.dao;

import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CacheDataDAO {
    List<CacheDataCommonRegionVO> selectCommonRegionList();

    List<CacheDataCommonEnvCodeVO> selectCommonEnvCodeList();

    List<CacheDataCommonLanguageCodeVO> selectCommonLanguageCodeList();

    List<CacheDataManageResourceVO> selectManageResourceList();

}
