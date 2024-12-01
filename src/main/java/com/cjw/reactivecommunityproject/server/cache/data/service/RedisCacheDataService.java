package com.cjw.reactivecommunityproject.server.cache.data.service;

import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;

import java.util.List;

public interface RedisCacheDataService {
    List<CacheDataCommonRegionVO> getCacheCommonRegionList();
    List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList();
    List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList();
    List<CacheDataManageResourceVO> getCacheManageResourceList();
}
