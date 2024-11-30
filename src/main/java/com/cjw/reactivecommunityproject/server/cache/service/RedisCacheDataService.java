package com.cjw.reactivecommunityproject.server.cache.service;

import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonRegionVO;

import java.util.List;

public interface RedisCacheDataService {
    List<CacheCommonRegionVO> getCacheCommonRegionList();
}
