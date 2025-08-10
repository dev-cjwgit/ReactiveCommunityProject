package com.cjw.reactivecommunityproject.server.batch.cache.data.service;

import com.cjw.reactivecommunityproject.server.batch.cache.data.model.BatchCacheDataVO;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BatchCacheDataReloadService {
    Flux<String> getTargetTable();
    Flux<BatchCacheDataVO> getCacheData(String targetCacheDataMethodName);
    Flux<String> getMethodNameByCompareDbAndCacheUpdatedAt(BatchCacheDataVO batchCacheDataVO);
    Mono<CacheManageResetVO> createCacheManageResetVO(List<String> batchCacheDataList);
    void publishCacheManageReset(CacheManageResetVO cacheManageResetVO);
}
