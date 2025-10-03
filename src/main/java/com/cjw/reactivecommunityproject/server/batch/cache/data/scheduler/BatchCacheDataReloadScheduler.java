package com.cjw.reactivecommunityproject.server.batch.cache.data.scheduler;

import com.cjw.reactivecommunityproject.server.batch.cache.data.service.BatchCacheDataReloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableAsync
@EnableScheduling
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
        prefix = "rc.scheduler.batch-cache-reload",
        name = "enabled",
        havingValue = "true"
)
public class BatchCacheDataReloadScheduler {
    private final BatchCacheDataReloadService batchCacheDataReloadService;

    @Scheduled(cron = "${rc.scheduler.batch-cache-reload.cron}")
    public void batchCacheDataReloadSchedulerConfig() {
        batchCacheDataReloadService.getTargetTable()
                .flatMap(batchCacheDataReloadService::getCacheData)
                .flatMap(batchCacheDataReloadService::getMethodNameByCompareDbAndCacheUpdatedAt)
                .collectList()
                .flatMap(batchCacheDataReloadService::createCacheManageResetVO)
                .doOnNext(batchCacheDataReloadService::publishCacheManageReset)
                .onErrorContinue((error, response) -> log.error("batchCacheDataReloadSchedulerConfig Error : {}", response, error))
                .subscribe();
    }
}
