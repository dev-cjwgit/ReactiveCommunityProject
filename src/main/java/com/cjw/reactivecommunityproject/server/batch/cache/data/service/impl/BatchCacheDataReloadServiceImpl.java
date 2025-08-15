package com.cjw.reactivecommunityproject.server.batch.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.batch.cache.data.model.BatchCacheDataVO;
import com.cjw.reactivecommunityproject.server.batch.cache.data.service.BatchCacheDataReloadService;
import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import com.cjw.reactivecommunityproject.server.cache.info.data.mapper.CacheInfoDataMapper;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoDataTableEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.reset.model.CacheManageResetVO;
import jakarta.annotation.Nullable;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class BatchCacheDataReloadServiceImpl implements BatchCacheDataReloadService {
    public static final String SELECT_METHOD_PREFIX = "select";
    public static final String GET_METHOD_PREFIX = "get";

    private final CacheInfoDataMapper cacheInfoDataMapper;
    private final CacheInfoDataService cacheInfoDataService;

    private final ApplicationEventPublisher publisher;

    @NonNull
    @Override
    public Flux<String> getTargetTable() {
        return Flux.fromArray(CacheManageCommonInfoDataTableEnum.values())
                .map(CacheManageCommonInfoDataTableEnum::getMethodName);
    }

    @NonNull
    private String convertSnakeCaseToCamelCase(@NonNull String rawName) {
        return Arrays.stream(rawName.toLowerCase().split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining());
    }

    @NonNull
    private String createMethodName(@NonNull String prefix, @NonNull String methodName) {
        return StringUtils.join(prefix, this.convertSnakeCaseToCamelCase(methodName));
    }

    @Nullable
    private Object invokeService(@NonNull Object service, @NonNull String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            return method.invoke(service);
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private List<CacheInfoDataUpdatable> toUpdatableList(@NonNull List<?> list) {
        if (!list.stream().allMatch(CacheInfoDataUpdatable.class::isInstance)) {
            return null;
        }
        return list.stream().map(e -> (CacheInfoDataUpdatable) e).toList();
    }

    @NonNull
    @Override
    public Flux<BatchCacheDataVO> getCacheData(@NonNull String targetCacheDataMethodName) {
        return Flux.just(targetCacheDataMethodName)
                .flatMap(cacheMethodName -> {
                    if (StringUtils.isBlank(cacheMethodName)) {
                        return Mono.empty();
                    }
                    Object dbMono = this.invokeService(cacheInfoDataMapper, this.createMethodName(SELECT_METHOD_PREFIX, cacheMethodName));
                    Object cacheMono = this.invokeService(cacheInfoDataService, this.createMethodName(GET_METHOD_PREFIX, cacheMethodName));
                    if (dbMono == null || cacheMono == null) {
                        return Mono.empty();
                    }
                    return Mono.just(BatchCacheDataVO.builder()
                            .cacheMethodName(cacheMethodName)
                            .dbData(dbMono)
                            .cacheData(cacheMono)
                            .build());
                });
    }

    // DB 보다 Cache 가 더 최신 데이터인지 체크하는 로직
    private boolean isDbNewerThanCache(@Nullable ZonedDateTime dbMaxUpdateAt, @Nullable ZonedDateTime cacheMaxUpdateAt) {
        return dbMaxUpdateAt != null && (cacheMaxUpdateAt == null || dbMaxUpdateAt.isAfter(cacheMaxUpdateAt));
    }

    @Nullable
    @Override
    public Flux<String> getMethodNameByCompareDbAndCacheUpdatedAt(@NonNull BatchCacheDataVO batchCacheDataVO) {
        return Flux.just(batchCacheDataVO)
                .flatMap(tuple -> {
                    if (tuple.dbData() instanceof List<?> dbObjList && tuple.cacheData() instanceof List<?> cacheObjList) {
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

                        if (this.isDbNewerThanCache(dbMaxUpdateAt, cacheMaxUpdateAt)) {
                            return Flux.just(tuple.cacheMethodName());
                        }
                    }
                    return Flux.empty();
                });
    }

    @NonNull
    @Override
    public Mono<CacheManageResetVO> createCacheManageResetVO(@NonNull List<String> changedDataCacheTable) {
        if (CollectionUtils.isEmpty(changedDataCacheTable)) {
            return Mono.empty();
        }

        return Mono.just(CacheManageResetVO.builder()
                .type(CacheManageCommonInfoTypeEnum.DATA)
                .table(changedDataCacheTable)
                .build());
    }

    @Override
    public void publishCacheManageReset(@NonNull CacheManageResetVO cacheManageResetVO) {
        publisher.publishEvent(cacheManageResetVO);
    }
}
