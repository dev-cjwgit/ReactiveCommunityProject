package com.cjw.reactivecommunityproject.server.cache.info.custom.service.impl;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import jakarta.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Strings;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheInfoCustomServiceImpl implements CacheInfoCustomService {
    private final RcProperties rcProperties;
    private final CacheInfoDataService cacheInfoDataService;

    @Nullable
    @Override
    @Cacheable(value = "rc_common_env_code", key = "'envId=' + #envId", cacheManager = "redisCacheManager")
    public CacheInfoCustomEnvCodeVO getCommonEnvCode(@NonNull String envId) {
        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Strings.CS.equals(o.getId(), envId))
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

    @NonNull
    @Override
    @Cacheable(value = "rc_common_env_code", key = "'category=' + #category", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomEnvCodeVO> getCommonEnvCodeByCategoryList(@NonNull String category) {
        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonEnvCodeList())
                .parallelStream()
                .filter(o -> Strings.CS.equals(o.getCategory(), category))
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
                .toList();
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonEnvCodeByCategoryList() {
        log.info("CacheInfoCustomServiceImpl.clearCommonCustomEnvCodeByCategoryList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "custom_common_language", key = "'path=' + #path + '_' + 'language=' + #language", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomLanguageVO> getCommonLanguageList(@NonNull String path, @NonNull String language) {
        var gbCodeList = cacheInfoDataService.getCommonLanguageGbCodeList();

        return CollectionUtils.emptyIfNull(cacheInfoDataService.getCommonLanguageCodeList())
                .parallelStream()
                .map(o -> {
                    var gbCode = CollectionUtils.emptyIfNull(gbCodeList)
                            .parallelStream()
                            .filter(o1 -> Strings.CS.equals(o1.getLanguage(), language))
                            .filter(o1 -> Strings.CS.equals(o.getPath(), o1.getPath()) && Strings.CS.equals(o.getCode(), o1.getCode()))
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
                .toList();

    }

    @Override
    @CacheEvict(value = "custom_common_language", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonLanguageList() {
        log.info("CacheInfoCustomServiceImpl.clearCommonCustomLanguageList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "custom_manage_role_function", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomRoleFunctionVO> getManageRoleFunctionList(@NonNull Integer roleUid) {
        var functionList = cacheInfoDataService.getManageFunctionList();
        return cacheInfoDataService.getManageRoleFunctionList()
                .parallelStream()
                .filter(roleFunction -> NumberUtils.compare(roleFunction.getRoleUid(), roleUid) == 0)
                .filter(roleFunction -> roleFunction.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleFunction -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(function -> NumberUtils.compare(roleFunction.getFunctionUid(), function.getUid()) == 0)
                        .filter(function -> function.getEnabled() == CommonEnabledEnum.Y)
                        .map(function -> CacheInfoCustomRoleFunctionVO.builder()
                                .roleUid(roleFunction.getRoleUid())
                                .functionUid(function.getUid())
                                .enabled(roleFunction.getEnabled())
                                .functionName(function.getName())
                                .functionType(function.getType())
                                .functionUpdatedUtcAt(roleFunction.getUpdatedUtcAt())
                                .build()
                        )
                )
                .toList();
    }

    @Override
    @CacheEvict(value = "custom_manage_role_function", allEntries = true, cacheManager = "redisCacheManager")
    public void clearManageRoleFunctionList() {
        log.info("CacheInfoCustomServiceImpl.clearCustomManageRoleFunctionList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "custom_manage_role_resource", key = "'path=' + #roleUid", cacheManager = "redisCacheManager")
    public List<CacheInfoCustomRoleResourceVO> getManageRoleResourceList(@NonNull Integer roleUid) {
        var functionList = cacheInfoDataService.getManageResourceList();
        return cacheInfoDataService.getManageRoleResourceList()
                .parallelStream()
                .filter(roleResourceVO -> NumberUtils.compare(roleResourceVO.getRoleUid() , roleUid) == 0)
                .filter(roleResourceVO -> roleResourceVO.getEnabled() == CommonEnabledEnum.Y)
                .flatMap(roleResourceVO -> CollectionUtils.emptyIfNull(functionList)
                        .parallelStream()
                        .filter(resource -> NumberUtils.compare(roleResourceVO.getResourceUid(), resource.getUid()) == 0)
                        .filter(resource -> resource.getEnabled() == CommonEnabledEnum.Y)
                        .map(resource -> CacheInfoCustomRoleResourceVO.builder()
                                .roleUid(roleResourceVO.getRoleUid())
                                .resourceUid(resource.getUid())
                                .method(resource.getMethod())
                                .urlPattern(resource.getUrlPattern())
                                .enabled(roleResourceVO.getEnabled())
                                .roleResourceUpdatedUtcAt(roleResourceVO.getUpdatedUtcAt())
                                .build()
                        )
                )
                .toList();
    }

    @Override
    @CacheEvict(value = "custom_manage_role_resource", allEntries = true, cacheManager = "redisCacheManager")
    public void clearManageRoleResourceList() {
        log.info("CacheInfoCustomServiceImpl.clearCustomManageRoleResourceList()");
    }
}
