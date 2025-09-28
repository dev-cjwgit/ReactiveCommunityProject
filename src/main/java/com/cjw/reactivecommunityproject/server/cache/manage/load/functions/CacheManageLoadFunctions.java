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
    /*
    갱신 할 캐시 타입 (데이터, 커스텀)
    갱신 할 테이블 목록
    각 CacheInfoDataService 또는 CacheInfoCustomService 의 get* 메소드를 호출
    파라미터 필요 시 파라미터 바인딩
    
    {
        "type": "DATA"
        , "table": [
            {
                "table_name": "COMMON_ENV_CODE_LIST",
                "parameters": []
            },
            {
                "table_name": "COMMON_LANGUAGE_GB_CODE_LIST",
                "parameters": ["KOR"]
            }
        ]
    }


    {
        "type": "CUSTOM"
        , "table": [
            {
                "table_name": "COMMON_ENV_CODE",
                "parameters": ["qwer.test"]
            },
            {
                "table_name": "COMMON_ENV_CODE_BY_CATEGORY_LIST",
                "parameters": ["mail.config"]
            },
            {
                "table_name": "COMMON_LANGUAGE_LIST",
                "parameters": ["QQ", "KOR"]
            },
            {
                "table_name": "MANAGE_ROLE_RESOURCE_LIST",
                "parameters": [0]
            }
        ]
    }
     */
}
