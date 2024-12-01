package com.cjw.reactivecommunityproject.server.cache.data.service;

import com.cjw.reactivecommunityproject.server.cache.data.model.*;

import java.util.List;

public interface CacheDataService {
    List<CacheDataCommonRegionVO> getCacheCommonRegionList();
    List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList();
    List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList();
    List<CacheDataCommonLanguageGbCodeVO> getCacheCommonLanguageGbCodeList(String lang);
    List<CacheDataManageResourceVO> getCacheManageResourceList();
}
