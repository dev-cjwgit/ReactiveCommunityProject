package com.cjw.reactivecommunityproject.server.cache.custom.service.impl;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheCustomServiceImpl implements CacheCustomService {
    private final CacheDataService cacheDataService;

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'path=' + #path + '_' + 'code=' + #code", cacheManager = "redisCacheManager")
    public CacheCustomEnvCodeVO getCommonCustomEnvCode(String path, String code) {
        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonEnvCodeList())
                .stream()
                .filter(o -> StringUtils.equals(o.getPath(), path) && StringUtils.equals(o.getCode(), code))
                .map(o -> CacheCustomEnvCodeVO.builder()
                        .path(o.getPath())
                        .code(o.getCode())
                        .value(o.getValue())
                        .order(o.getOrder())
                        .category(o.getCategory())
                        .updatedUtcAt(o.getUpdatedUtcAt())
                        .build())
                .findFirst()
                .orElse(null);
    }

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'category=' + #category", cacheManager = "redisCacheManager")
    public List<CacheCustomEnvCodeVO> getCommonCustomEnvCodeByCategory(String category) {
        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonEnvCodeList())
                .stream()
                .filter(o -> StringUtils.equals(o.getCategory(), category))
                .map(o -> CacheCustomEnvCodeVO.builder()
                        .path(o.getPath())
                        .code(o.getCode())
                        .value(o.getValue())
                        .order(o.getOrder())
                        .category(o.getCategory())
                        .updatedUtcAt(o.getUpdatedUtcAt())
                        .build())
                .sorted(Comparator.comparing(CacheCustomEnvCodeVO::getOrder, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }
}
