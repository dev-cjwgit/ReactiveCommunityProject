package com.cjw.reactivecommunityproject.server.cache.reset.service.impl;

import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import com.cjw.reactivecommunityproject.server.cache.reset.exception.CacheResetErrorMessage;
import com.cjw.reactivecommunityproject.server.cache.reset.exception.CacheResetException;
import com.cjw.reactivecommunityproject.server.cache.reset.interfaces.CacheResetTableNaming;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetCustomTableEnum;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetTableVO;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.reset.model.CacheResetVO;
import com.cjw.reactivecommunityproject.server.cache.reset.service.CacheResetService;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheResetServiceImpl implements CacheResetService {
    public static final String RESET_METHOD_PREFIX = "clear";

    private final CacheDataService cacheDataService;
    private final CacheCustomService cacheCustomService;


    @Override
    public Flux<CacheResetTableVO> createCacheResetTableVO(CacheResetVO cacheResetVO) {
        var type = cacheResetVO.type();

        if (type == null) {
            return Flux.error(new CacheResetException(CacheResetErrorMessage.INVALID_TYPE));
        }

        return Flux.fromIterable(CollectionUtils.emptyIfNull(cacheResetVO.table()))
                .flatMap(table -> {
                    try {
                        CacheResetTableNaming enumValue = switch (type) {
                            case DATA -> CacheResetDataTableEnum.valueOf(table);
                            case CUSTOM -> CacheResetCustomTableEnum.valueOf(table);
                        };

                        return Mono.just(CacheResetTableVO.builder()
                                .type(type)
                                .table(enumValue)
                                .build());
                    } catch (IllegalArgumentException ex) {
                        log.warn("Invalid table enum value: [{}] for type [{}]", table, type);
                        return Mono.empty();
                    }
                });
    }

    private Object resolveService(CacheResetTypeEnum type) {
        return switch (type) {
            case DATA -> cacheDataService;
            case CUSTOM -> cacheCustomService;
        };
    }

    private String createClearMethodName(String tableName) {
        return StringUtils.join(RESET_METHOD_PREFIX, this.toCamelCase(tableName));
    }

    private String toCamelCase(String rawName) {
        return Arrays.stream(rawName.toLowerCase().split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining());
    }

    private void invokeMethod(Object service, String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            method.invoke(service);
        } catch (Exception e) {
            log.error("Failed to invoke method: {}", methodName, e);
        }
    }

    @Override
    public void resetCache(CacheResetTableVO cacheResetTableVO) {
        String methodName = this.createClearMethodName(cacheResetTableVO.table().getTableName());
        Object service = this.resolveService(cacheResetTableVO.type());

        this.invokeMethod(service, methodName);
    }
}
