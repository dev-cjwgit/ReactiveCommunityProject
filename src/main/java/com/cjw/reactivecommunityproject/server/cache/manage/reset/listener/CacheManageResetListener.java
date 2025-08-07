package com.cjw.reactivecommunityproject.server.cache.manage.reset.listener;

import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.service.CacheManageResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheManageResetListener {
    private final CacheManageResetService cacheManageResetService;

    @EventListener(CacheManageResetVO.class)
    public Mono<Void> listener(CacheManageResetVO cacheManageResetVO) {
        return Mono.just(cacheManageResetVO)
                .log("CacheManageResetListener.listener() : start")
                .flatMapMany(cacheManageResetService::createTableVO)
                .doOnNext(cacheManageResetService::reset)
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error))
                .doFinally(st -> log.info("CacheManageResetListener.listener() : end | signal : {}", st))
                .then();
    }
}
