package com.cjw.reactivecommunityproject.server.batch.cache.data.service;

import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BatchCacheDataReloadService {
    Flux<String> getTargetTable();
    Flux<String> getChangedDataCacheTable(String targetTable);
    Mono<CacheManageResetVO> createCacheManageResetVO(List<String> changedDataCacheTable);
    void publishCacheManageReset(CacheManageResetVO cacheManageResetVO);
}
