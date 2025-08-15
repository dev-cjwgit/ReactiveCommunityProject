package com.cjw.reactivecommunityproject.server.cache.info.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.info.data.mapper.CacheInfoDataMapper;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.service.CacheInfoDataService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheInfoDataServiceImpl implements CacheInfoDataService {
    private final CacheInfoDataMapper cacheInfoDataMapper;

    @NonNull
    @Override
    @Cacheable(value = "rc_common_region", cacheManager = "redisCacheManager")
    public List<CacheInfoDataCommonRegionVO> getCommonRegionList() {
        return cacheInfoDataMapper.selectCommonRegionList();
    }

    @Override
    @CacheEvict(value = "rc_common_region", cacheManager = "redisCacheManager")
    public void clearCommonRegionList() {
        log.info("CacheInfoDataServiceImpl.clearCacheCommonRegionList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public List<CacheInfoDataCommonEnvCodeVO> getCommonEnvCodeList() {
        return cacheInfoDataMapper.selectCommonEnvCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public void clearCommonEnvCodeList() {
        log.info("CacheInfoDataServiceImpl.clearCacheCommonEnvCodeList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public List<CacheInfoDataCommonLanguageCodeVO> getCommonLanguageCodeList() {
        return cacheInfoDataMapper.selectCommonLanguageCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public void clearCommonLanguageCodeList() {
        log.info("CacheInfoDataServiceImpl.clearCacheCommonLanguageCodeList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_common_language_gb_code", cacheManager = "redisCacheManager")
    public List<CacheInfoDataCommonLanguageGbCodeVO> getCommonLanguageGbCodeList() {
        return cacheInfoDataMapper.selectCommonLanguageGbCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_language_gb_code", cacheManager = "redisCacheManager")
    public void clearCommonLanguageGbCodeList() {
        log.info("CacheInfoDataServiceImpl.clearCacheCommonLanguageGbCodeList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public List<CacheInfoDataManageResourceVO> getManageResourceList() {
        return cacheInfoDataMapper.selectManageResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public void clearManageResourceList() {
        log.info("CacheInfoDataServiceImpl.clearCacheManageResourceList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public List<CacheInfoDataManageFunctionVO> getManageFunctionList() {
        return cacheInfoDataMapper.selectManageFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public void clearManageFunctionList() {
        log.info("CacheInfoDataServiceImpl.clearCacheManageFunctionList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public List<CacheInfoDataManageRoleFunctionVO> getManageRoleFunctionList() {
        return cacheInfoDataMapper.selectManageRoleFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public void clearManageRoleFunctionList() {
        log.info("CacheInfoDataServiceImpl.clearCacheManageRoleFunctionList()");
    }

    @NonNull
    @Override
    @Cacheable(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public List<CacheInfoDataManageRoleResourceVO> getManageRoleResourceList() {
        return cacheInfoDataMapper.selectManageRoleResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public void clearManageRoleResourceList() {
        log.info("CacheInfoDataServiceImpl.clearManageRoleResourceList()");
    }
}
