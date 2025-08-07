package com.cjw.reactivecommunityproject.server.cache.manage.reset.service;

import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetTableVO;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import reactor.core.publisher.Flux;

public interface CacheManageResetService {
    Flux<CacheManageResetTableVO> createTableVO(CacheManageResetVO cacheManageResetVO);

    void reset(CacheManageResetTableVO cacheManageResetTableVO);
}
