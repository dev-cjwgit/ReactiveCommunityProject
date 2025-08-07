package com.cjw.reactivecommunityproject.server.cache.manage.load.functions;

import com.cjw.reactivecommunityproject.server.cache.manage.load.model.CacheManageLoadVO;
import com.cjw.reactivecommunityproject.server.cache.manage.load.service.CacheManageLoadService;
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
public class CacheManageLoadFunctions {
    private final CacheManageLoadService cacheManageLoadService;

    @Bean
    public Function<Flux<CacheManageLoadVO>, Mono<Void>> cacheManageLoadFunction() {
        return flux -> flux
                .flatMap(cacheManageLoadService::createTableVO)
                .doOnNext(cacheManageLoadService::load)
                .then()
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error));
    }
}
