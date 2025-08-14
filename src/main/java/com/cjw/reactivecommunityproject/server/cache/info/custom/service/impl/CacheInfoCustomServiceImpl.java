package com.cjw.reactivecommunityproject.server.cache.info.custom.service.impl;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheInfoCustomServiceImpl implements CacheInfoCustomService {
    private final RcProperties rcProperties;
    private final CacheInfoDataService cacheInfoDataService;

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'envId=' + #envId", cacheManager = "redisCacheManager")
    public CacheInfoCustomEnvCodeVO getCommonEnvCode(String envId) {
        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Objects.equals(o.getId(), envId))
                .map(o -> CacheInfoCustomEnvCodeVO.builder()
                        .region(o.getRegion())
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
    public void clearCommonEnvCode() {
        log.info("CacheInfoCustomServiceImpl.clearCommonCustomEnvCode()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", key = "'category=' + #category", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomEnvCodeVO> getCommonEnvCodeByCategoryList(String category) {
        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Objects.equals(o.getCategory(), category))
                .map(o -> CacheInfoCustomEnvCodeVO.builder()
                        .region(o.getRegion())
                        .id(o.getId())
                        .type(o.getType())
                        .value(o.getValue())
                        .order(o.getOrder())
                        .category(o.getCategory())
                        .enabled(o.getEnabled())
                        .updatedUtcAt(o.getUpdatedUtcAt())
                        .build())
                .sorted(Comparator.comparing(CacheInfoCustomEnvCodeVO::getOrder, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonEnvCodeByCategoryList() {
        log.info("CacheInfoCustomServiceImpl.clearCommonCustomEnvCodeByCategoryList()");
    }

    @Override
    @Cacheable(value = "custom_common_language", key = "'path=' + #path + '_' + 'language=' + #language", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomLanguageVO> getCommonLanguageList(String path, String language) {
        var gbCodeList = cacheInfoDataService.getCommonLanguageGbCodeList();

        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonLanguageCodeList())
                .parallelStream()
                .map(o -> {
                    var gbCode = CollectionUtils.emptyIfNull(gbCodeList)
                            .parallelStream()
                            .filter(o1 -> Strings.CS.equals(o1.getLanguage(), language))
                            .filter(o1 -> Objects.equals(o.getPath(), o1.getPath()) && Objects.equals(o.getCode(), o1.getCode()))
                            .findFirst()
                            .orElse(null);

                    return CacheInfoCustomLanguageVO.builder()
                            .path(o.getPath())
                            .code(o.getCode())
                            .lang(gbCode != null ? gbCode.getLanguage() : rcProperties.config().defaultLanguage())
                            .value(gbCode != null ? gbCode.getValue() : o.getValue())
                            .updatedUtcAt(gbCode != null ? gbCode.getUpdatedUtcAt() : o.getUpdatedUtcAt())
                            .build();
                })
                .collect(Collectors.toList());

    }

    @Override
    @CacheEvict(value = "custom_common_language", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonLanguageList() {
        log.info("CacheInfoCustomServiceImpl.clearCommonCustomLanguageList()");
    }

    @Override
    @Cacheable(value = "custom_manage_role_function", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomRoleFunctionVO> getManageRoleFunctionList(Integer roleUid) {
        var functionList = cacheInfoDataService.getManageFunctionList();
        return cacheInfoDataService.getManageRoleFunctionList()
                .parallelStream()
                .filter(roleFunction -> Objects.equals(roleFunction.getRoleUid(), roleUid))
                .filter(roleFunction -> roleFunction.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleFunction -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(function -> Objects.equals(roleFunction.getFunctionUid(), function.getUid()))
                        .filter(function -> function.getEnabled() == CommonEnabledEnum.Y)
                        .map(function -> CacheInfoCustomRoleFunctionVO.builder()
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
    public void clearManageRoleFunctionList() {
        log.info("CacheInfoCustomServiceImpl.clearCustomManageRoleFunctionList()");
    }

    @Override
    @Cacheable(value = "custom_manage_role_resource", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomRoleResourceVO> getManageRoleResourceList(Integer roleUid) {
        var functionList = cacheInfoDataService.getManageResourceList();
        return cacheInfoDataService.getManageRoleResourceList()
                .parallelStream()
                .filter(roleFunction -> Objects.equals(roleFunction.getRoleUid(), roleUid))
                .filter(roleFunction -> roleFunction.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleFunction -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(resource -> Objects.equals(roleFunction.getResourceUid(), resource.getUid()))
                        .filter(resource -> resource.getEnabled() == CommonEnabledEnum.Y)
                        .map(resource -> CacheInfoCustomRoleResourceVO.builder()
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
    public void clearManageRoleResourceList() {
        log.info("CacheInfoCustomServiceImpl.clearCustomManageRoleResourceList()");
    }
}
