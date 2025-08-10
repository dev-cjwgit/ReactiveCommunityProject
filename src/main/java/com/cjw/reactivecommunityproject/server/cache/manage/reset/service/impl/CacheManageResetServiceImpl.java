package com.cjw.reactivecommunityproject.server.cache.manage.reset.service.impl;

import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import com.cjw.reactivecommunityproject.server.cache.manage.common.interfaces.CacheManageCommonTableNaming;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoCustomTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetTableVO;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.service.CacheManageResetService;
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
public class CacheManageResetServiceImpl implements CacheManageResetService {
    public static final String RESET_METHOD_PREFIX = "clear";

    private final CacheInfoDataService cacheInfoDataService;
    private final CacheInfoCustomService cacheInfoCustomService;


    @Override
    public Flux<CacheManageResetTableVO> createTableVO(CacheManageResetVO cacheManageResetVO) {
        var type = cacheManageResetVO.type();

        if (type == null) {
            log.warn("type is null");
            return Flux.empty();
        }

        return Flux.fromIterable(CollectionUtils.emptyIfNull(cacheManageResetVO.table()))
                .flatMap(table -> {
                    try {
                        CacheManageCommonTableNaming enumValue = switch (type) {
                            case DATA -> CacheManageCommonInfoDataTableEnum.valueOf(table);
                            case CUSTOM -> CacheManageCommonInfoCustomTableEnum.valueOf(table);
                        };

                        return Mono.just(CacheManageResetTableVO.builder()
                                .type(type)
                                .table(enumValue)
                                .build());
                    } catch (IllegalArgumentException ex) {
                        log.warn("Invalid table enum value: [{}] for type [{}]", table, type);
                        return Mono.empty();
                    }
                });
    }

    private Object resolveService(CacheManageCommonInfoTypeEnum type) {
        return switch (type) {
            case DATA -> cacheInfoDataService;
            case CUSTOM -> cacheInfoCustomService;
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
    public void reset(CacheManageResetTableVO cacheManageResetTableVO) {
        String methodName = this.createClearMethodName(cacheManageResetTableVO.table().getMethodName());
        Object service = this.resolveService(cacheManageResetTableVO.type());

        this.invokeMethod(service, methodName);
    }
}
