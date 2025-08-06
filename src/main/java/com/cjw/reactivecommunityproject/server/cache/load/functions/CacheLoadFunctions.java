package com.cjw.reactivecommunityproject.server.cache.load.functions;

import com.cjw.reactivecommunityproject.server.cache.load.model.CacheLoadVO;
import com.cjw.reactivecommunityproject.server.cache.load.service.CacheLoadService;
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
public class CacheLoadFunctions {
    private final CacheLoadService cacheLoadService;

    @Bean
    public Function<Flux<CacheLoadVO>, Mono<Void>> cacheLoadFunction() {
        return flux -> flux
                .flatMap(cacheLoadService::createCacheLoadTableVO)
                .doOnNext(cacheLoadService::loadCache)
                .then()
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error));
    }
}
