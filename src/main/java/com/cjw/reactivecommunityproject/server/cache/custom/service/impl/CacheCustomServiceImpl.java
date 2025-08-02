package com.cjw.reactivecommunityproject.server.cache.custom.service.impl;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleResourceVO;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheCustomServiceImpl implements CacheCustomService {
    private final RcProperties rcProperties;
    private final CacheDataService cacheDataService;

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'envId=' + #envId", cacheManager = "redisCacheManager")
    public CacheCustomEnvCodeVO getCustomCommonEnvCode(String envId) {
        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Objects.equals(o.getId(), envId))
                .map(o -> CacheCustomEnvCodeVO.builder()
                        .id(o.getId())
                        .type(o.getType())
                        .value(o.getValue())
                        .order(o.getOrder())
                        .category(o.getCategory())
                        .enabled(o.getEnabled())
                        .updatedUtcAt(o.getUpdatedUtcAt())
                        .build())
                .findFirst()
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCustomCommonEnvCode() {
        log.info("CacheCustomServiceImpl.clearCommonCustomEnvCode()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'category=' + #category", cacheManager = "redisCacheManager")
    public List<CacheCustomEnvCodeVO> getCustomCommonEnvCodeByCategoryList(String category) {
        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Objects.equals(o.getCategory(), category))
                .map(o -> CacheCustomEnvCodeVO.builder()
                        .id(o.getId())
                        .type(o.getType())
                        .value(o.getValue())
                        .order(o.getOrder())
                        .category(o.getCategory())
                        .enabled(o.getEnabled())
                        .updatedUtcAt(o.getUpdatedUtcAt())
                        .build())
                .sorted(Comparator.comparing(CacheCustomEnvCodeVO::getOrder, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCustomCommonEnvCodeByCategoryList() {
        log.info("CacheCustomServiceImpl.clearCommonCustomEnvCodeByCategoryList()");
    }

    @Override
    @Cacheable(value = "custom_common_language", key = "'path=' + #path + '_' + 'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheCustomLanguageVO> getCustomCommonLangaugeList(String path, String lang) {
        var gbCodeList = cacheDataService.getCacheCommonLanguageGbCodeList(lang);

        return CollectionUtils.emptyIfNull(cacheDataService.getCacheCommonLanguageCodeList())
                .parallelStream()
                .map(o -> {
                    var gbCode = CollectionUtils.emptyIfNull(gbCodeList)
                            .parallelStream()
                            .filter(o1 -> Objects.equals(o.getPath(), o1.getPath()) && Objects.equals(o.getCode(), o1.getCode()))
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
                .collect(Collectors.toList());

    }

    @Override
    @CacheEvict(value = "custom_common_language", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCustomCommonLanguageList() {
        log.info("CacheCustomServiceImpl.clearCommonCustomLanguageList()");
    }

    @Override
    @Cacheable(value = "custom_manage_role_function", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheCustomRoleFunctionVO> getCustomManageRoleFunctionList(Integer roleUid) {
        var functionList = cacheDataService.getCacheManageFunctionList();
        return cacheDataService.getCacheManageRoleFunctionList()
                .parallelStream()
                .filter(roleFunction -> Objects.equals(roleFunction.getRoleUid(), roleUid))
                .filter(roleFunction -> roleFunction.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleFunction -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(function -> Objects.equals(roleFunction.getFunctionUid(), function.getUid()))
                        .filter(function -> function.getEnabled() == CommonEnabledEnum.Y)
                        .map(function -> CacheCustomRoleFunctionVO.builder()
                                .roleUid(roleFunction.getRoleUid())
                                .functionUid(function.getUid())
                                .enabled(roleFunction.getEnabled())
                                .name(function.getName())
                                .type(function.getType())
                                .updatedUtcAt(roleFunction.getUpdatedUtcAt())
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "custom_manage_role_function", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCustomManageRoleFunctionList() {
        log.info("CacheCustomServiceImpl.clearCustomManageRoleFunctionList()");
    }

    @Override
    @Cacheable(value = "custom_manage_role_resource", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheCustomRoleResourceVO> getCustomManageRoleResourceList(Integer roleUid) {
        var functionList = cacheDataService.getCacheManageResourceList();
        return cacheDataService.getCacheManageRoleResourceList()
                .parallelStream()
                .filter(roleFunction -> Objects.equals(roleFunction.getRoleUid(), roleUid))
                .filter(roleFunction -> roleFunction.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleFunction -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(resource -> Objects.equals(roleFunction.getResourceUid(), resource.getUid()))
                        .filter(resource -> resource.getEnabled() == CommonEnabledEnum.Y)
                        .map(resource -> CacheCustomRoleResourceVO.builder()
                                .roleUid(roleFunction.getRoleUid())
                                .resourceUid(resource.getUid())
                                .method(resource.getMethod())
                                .urlPattern(resource.getUrlPattern())
                                .enabled(roleFunction.getEnabled())
                                .updatedUtcAt(roleFunction.getUpdatedUtcAt())
                                .build()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "custom_manage_role_resource", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCustomManageRoleResourceList() {
        log.info("CacheCustomServiceImpl.clearCustomManageRoleResourceList()");
    }
}
