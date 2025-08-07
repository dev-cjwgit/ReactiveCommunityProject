package com.cjw.reactivecommunityproject.server.cache.manage.reset.functions;

import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.service.CacheManageResetService;
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
public class CacheManageResetFunctions {
    private final CacheManageResetService cacheManageResetService;

    @Bean
    public Function<Flux<CacheManageResetVO>, Mono<Void>> cacheManageResetFunction() {
        return flux -> flux
                .flatMap(cacheManageResetService::createTableVO)
                .doOnNext(cacheManageResetService::reset)
                .then()
                .onErrorContinue((error, obj) -> log.error(StringUtils.join(error.getMessage(), " : ", obj), error));
    }
}
