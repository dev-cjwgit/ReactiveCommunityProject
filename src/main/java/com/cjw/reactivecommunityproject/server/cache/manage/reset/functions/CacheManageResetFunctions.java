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
    /*
    리셋 할 캐시 타입 (데이터, 커스텀)
    리셋 할 테이블 목록
    각 CacheInfoDataService 또는 CacheInfoCustomService 의 clear* 메소드를 호출
    {
        "type": "DATA"
        , "table": [
            "COMMON_REGION_LIST"
            , "COMMON_ENV_CODE_LIST"
            , "COMMON_LANGUAGE_CODE_LIST"
            , "COMMON_LANGUAGE_GB_CODE_LIST"
            , "MANAGE_RESOURCE_LIST"
            , "MANAGE_FUNCTION_LIST"
            , "MANAGE_ROLE_FUNCTION_LIST"
            , "MANAGE_ROLE_RESOURCE_LIST"
        ]
    }
    {
        "type": "CUSTOM"
        , "table": [
            "COMMON_ENV_CODE"
            , "COMMON_ENV_CODE_BY_CATEGORY_LIST"
            , "COMMON_LANGUAGE_LIST"
            , "MANAGE_ROLE_FUNCTION_LIST"
            , "MANAGE_ROLE_RESOURCE_LIST"
        ]
    }
     */
}
