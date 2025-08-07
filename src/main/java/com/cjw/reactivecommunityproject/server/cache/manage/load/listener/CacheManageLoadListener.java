package com.cjw.reactivecommunityproject.server.cache.manage.load.listener;

import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadVO;
import com.cjw.reactivecommunityproject.server.cache.manage.load.service.CacheManageLoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheManageLoadListener {
    private final CacheManageLoadService cacheManageLoadService;

    @EventListener(CacheManageLoadVO.class)
    public Mono<Void> listener(CacheManageLoadVO cacheManageLoadVO) {
        return Mono.just(cacheManageLoadVO)
                .log("CacheManageLoadListener.listener() : start")
                .flatMapMany(cacheManageLoadService::createTableVO)
                .doOnNext(cacheManageLoadService::load)
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error))
                .doFinally(st -> log.info("CacheManageLoadListener.listener() : end | signal : {}", st))
                .then();
    }
}
