package com.cjw.reactivecommunityproject.server.cache.load.listener;

import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadVO;
import com.cjw.reactivecommunityproject.server.cache.load.service.CacheLoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheLoadListener {
    private final CacheLoadService cacheLoadService;

    @EventListener(CacheLoadVO.class)
    public Mono<Void> listener(CacheLoadVO cacheLoadVO) {
        return Mono.just(cacheLoadVO)
                .log("CacheLoadListener.listener() : start")
                .flatMapMany(cacheLoadService::createCacheLoadTableVO)
                .doOnNext(cacheLoadService::loadCache)
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error))
                .doFinally(st -> log.info("CacheLoadListener.listener() : end | signal : {}", st))
                .then();
    }
}
