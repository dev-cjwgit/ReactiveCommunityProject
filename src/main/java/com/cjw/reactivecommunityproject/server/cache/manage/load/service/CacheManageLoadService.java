package com.cjw.reactivecommunityproject.server.cache.manage.load.service;

import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadTableVO;
import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadVO;
import reactor.core.publisher.Flux;

public interface CacheManageLoadService {
    Flux<CacheManageLoadTableVO> createTableVO(CacheManageLoadVO cacheManageLoadVO);

    void load(CacheManageLoadTableVO cacheManageLoadTableVO);
}
