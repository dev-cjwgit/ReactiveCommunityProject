package com.cjw.reactivecommunityproject.server.cache.reset.listener;

import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetVO;
import com.cjw.reactivecommunityproject.server.cache.reset.service.CacheResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheResetListener {
    private final CacheResetService cacheResetService;

    @EventListener(CacheResetVO.class)
    public Mono<Void> listener(CacheResetVO cacheLoadVO) {
        return Mono.just(cacheLoadVO)
                .log("CacheResetListener.listener() : start")
                .flatMapMany(cacheResetService::createCacheResetTableVO)
                .doOnNext(cacheResetService::resetCache)
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error))
                .doFinally(st -> log.info("CacheResetListener.listener() : end | signal : {}", st))
                .then();
    }
}
