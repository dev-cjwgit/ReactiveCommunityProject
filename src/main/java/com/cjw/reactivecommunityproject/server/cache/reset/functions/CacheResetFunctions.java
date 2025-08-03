package com.cjw.reactivecommunityproject.server.cache.reset.functions;

import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetVO;
import com.cjw.reactivecommunityproject.server.cache.reset.service.CacheResetService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CacheResetFunctions {
    private final CacheResetService cacheResetService;

    @Bean
    public Function<Flux<CacheResetVO>, Mono<Void>> cacheResetFunction() {
        return flux -> flux
                .flatMap(cacheResetService::createCacheResetTableVO)
                .doOnNext(cacheResetService::resetCache)
                .then()
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error));
    }
}
