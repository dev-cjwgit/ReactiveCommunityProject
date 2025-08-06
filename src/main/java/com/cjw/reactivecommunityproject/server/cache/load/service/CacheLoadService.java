package com.cjw.reactivecommunityproject.server.cache.load.service;

import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadTableVO;
import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadVO;
import reactor.core.publisher.Flux;

public interface CacheLoadService {
    Flux<CacheLoadTableVO> createCacheLoadTableVO(CacheLoadVO cacheLoadVO);

    void loadCache(CacheLoadTableVO cacheLoadTableVO);
}
