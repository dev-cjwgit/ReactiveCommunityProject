package com.cjw.reactivecommunityproject.server.batch.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.batch.cache.data.service.BatchCacheDataReloadService;
import com.cjw.reactivecommunityproject.server.cache.info.data.interfaces.CacheInfoDataUpdatable;
import com.cjw.reactivecommunityproject.server.cache.info.data.mapper.CacheInfoDataMapper;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchCacheDataReloadServiceImpl implements BatchCacheDataReloadService {
    public static final String SELECT_METHOD_PREFIX = "select";
    public static final String GET_METHOD_PREFIX = "get";

    private final CacheInfoDataMapper cacheInfoDataMapper;
    private final CacheInfoDataService cacheInfoDataService;

    private final ApplicationEventPublisher publisher;

    @Override
    public Flux<String> getTargetTable() {
        return Flux.fromArray(CacheManageCommonInfoDataTableEnum.values())
                .map(CacheManageCommonInfoDataTableEnum::getTableName);
    }

    private String toCamelCase(String rawName) {
        return Arrays.stream(rawName.toLowerCase().split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining());
    }

    private String createMethodName(String prefix, String tableName) {
        return StringUtils.join(prefix, this.toCamelCase(tableName));
    }

    private Object invokeService(Object service, String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            return method.invoke(service);
        } catch (Exception e) {
            return null;
        }
    }

    private List<CacheInfoDataUpdatable> toUpdatableList(List<?> list) {
        if (!list.stream().allMatch(CacheInfoDataUpdatable.class::isInstance)) {
            return null;
        }
        return list.stream().map(e -> (CacheInfoDataUpdatable) e).toList();
    }

    @Override
    public Flux<String> getChangedDataCacheTable(String targetTable) {
        return Flux.just(targetTable)
                .flatMap(table -> {
                    Object dbMono = this.invokeService(cacheInfoDataMapper, this.createMethodName(SELECT_METHOD_PREFIX, table));
                    Object cacheMono = this.invokeService(cacheInfoDataService, this.createMethodName(GET_METHOD_PREFIX, table));
                    if (dbMono == null || cacheMono == null) {
                        return Mono.empty();
                    }
                    return Mono.just(Tuples.of(table, dbMono, cacheMono));
                })
                .flatMap(tuple -> {
                    String table = tuple.getT1();
                    Object dbObj = tuple.getT2();
                    Object cacheObj = tuple.getT3();

                    if (dbObj instanceof List<?> dbObjList && cacheObj instanceof List<?> cacheObjList) {
                        List<CacheInfoDataUpdatable> dbList = this.toUpdatableList(dbObjList);
                        List<CacheInfoDataUpdatable> cacheList = this.toUpdatableList(cacheObjList);

                        ZonedDateTime dbMaxUpdateAt = CollectionUtils.emptyIfNull(dbList)
                                .parallelStream()
                                .map(CacheInfoDataUpdatable::getUpdatedUtcAt)
                                .filter(Objects::nonNull)
                                .max(Comparator.naturalOrder())
                                .orElse(null);

                        ZonedDateTime cacheMaxUpdateAt = CollectionUtils.emptyIfNull(cacheList)
                                .parallelStream()
                                .map(CacheInfoDataUpdatable::getUpdatedUtcAt)
                                .filter(Objects::nonNull)
                                .max(Comparator.naturalOrder())
                                .orElse(null);

                        if (dbMaxUpdateAt != null && (cacheMaxUpdateAt == null || dbMaxUpdateAt.isAfter(cacheMaxUpdateAt))) {
                            return Flux.just(table);
                        }
                    }
                    return Flux.empty();
                });
    }

    @Override
    public Mono<CacheManageResetVO> createCacheManageResetVO(List<String> changedDataCacheTable) {
        if (CollectionUtils.isEmpty(changedDataCacheTable)) {
            return Mono.empty();
        }

        return Mono.just(CacheManageResetVO.builder()
                .type(CacheManageCommonInfoTypeEnum.DATA)
                .table(changedDataCacheTable)
                .build());
    }

    @Override
    public void publishCacheManageReset(CacheManageResetVO cacheManageResetVO) {
        publisher.publishEvent(cacheManageResetVO);
    }
}
