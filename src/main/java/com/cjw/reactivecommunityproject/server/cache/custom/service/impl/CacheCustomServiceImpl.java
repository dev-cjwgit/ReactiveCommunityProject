package com.cjw.reactivecommunityproject.server.cache.custom.service.impl;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheCustomServiceImpl implements CacheCustomService {
    private final RcProperties rcProperties;
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
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonCustomEnvCode() {
        log.info("CacheCustomServiceImpl.clearCommonCustomEnvCode()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'category=' + #category", cacheManager = "redisCacheManager")
    public List<CacheCustomEnvCodeVO> getCommonCustomEnvCodeByCategoryList(String category) {
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

    @Override
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonCustomEnvCodeByCategoryList() {
        log.info("CacheCustomServiceImpl.clearCommonCustomEnvCodeByCategoryList()");
    }

    @Override
    @Cacheable(value = "custom_common_language", key = "'path=' + #path + '_' + 'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheCustomLanguageVO> getCommonCustomLangaugeList(String path, String lang) {
        var gbCodeList = cacheDataService.getCacheCommonLanguageGbCodeList(lang);

        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonLanguageCodeList())
                .stream()
                .map(o -> {
                    var gbCode = CollectionUtils.emptyIfNull(gbCodeList)
                            .parallelStream()
                            .filter(o1 -> StringUtils.equals(o.getPath(), o1.getPath()) && StringUtils.equals(o.getCode(), o1.getCode()))
                            .findFirst()
                            .orElse(null);

                    return CacheCustomLanguageVO.builder()
                            .path(o.getPath())
                            .code(o.getCode())
                            .lang(gbCode != null ? gbCode.getLang() : rcProperties.config().defaultLanguage())
                            .value(gbCode != null ? gbCode.getValue() : o.getValue())
                            .updatedUtcAt(gbCode != null ? gbCode.getUpdatedUtcAt() : o.getUpdatedUtcAt())
                            .build();
                })
                .toList();
    }

    @Override
    @CacheEvict(value = "custom_common_language", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonCustomLanguageList() {
        log.info("CacheCustomServiceImpl.clearCommonCustomLanguageList()");
    }
}
