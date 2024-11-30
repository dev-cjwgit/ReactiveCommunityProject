package com.cjw.reactivecommunityproject.server.cache.service;

import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheManageResourceVO;

import java.util.List;

public interface RedisCacheDataService {
    List<CacheCommonRegionVO> getCacheCommonRegionList();
    List<CacheCommonEnvCodeVO> getCacheCommonEnvCodeList();
    List<CacheCommonLanguageCodeVO> getCacheCommonLanguageCodeList();
    List<CacheManageResourceVO> getCacheManageResourceList();
}
