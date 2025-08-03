package com.cjw.reactivecommunityproject.server.cache.reset.service;

import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetTableVO;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetVO;
import reactor.core.publisher.Flux;

public interface CacheResetService {
    Flux<CacheResetTableVO> createCacheResetTableVO(CacheResetVO cacheResetVO);

    void resetCache(CacheResetTableVO cacheResetTableVO);
}
