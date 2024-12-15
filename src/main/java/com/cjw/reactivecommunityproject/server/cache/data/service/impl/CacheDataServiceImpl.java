package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.data.dao.CacheDataDAO;
import com.cjw.reactivecommunityproject.server.cache.data.model.*;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheDataServiceImpl implements CacheDataService {
    private final CacheDataDAO cacheDataDAO;

    @Override
    @Cacheable(value = "rc_common_region", cacheManager = "redisCacheManager")
    public List<CacheDataCommonRegionVO> getCacheCommonRegionList() {
        return cacheDataDAO.selectCommonRegionList();
    }

    @Override
    @CacheEvict(value = "rc_common_region", cacheManager = "redisCacheManager")
    public void clearCacheCommonRegionList() {
        log.info("CacheDataServiceImpl.clearCacheCommonRegionList()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList() {
        return cacheDataDAO.selectCommonEnvCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public void clearCacheCommonEnvCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonEnvCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList() {
        return cacheDataDAO.selectCommonLanguageCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public void clearCacheCommonLanguageCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_gb_code", key = "'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageGbCodeVO> getCacheCommonLanguageGbCodeList(String lang) {
        return cacheDataDAO.selectCommonLanguageGbCodeList(lang);
    }

    @Override
    @CacheEvict(value = "rc_common_language_gb_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCacheCommonLanguageGbCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageGbCodeList()");
    }

    @Override
    @Cacheable(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageResourceVO> getCacheManageResourceList() {
        return cacheDataDAO.selectManageResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public void clearCacheManageResourceList() {
        log.info("CacheDataServiceImpl.clearCacheManageResourceList()");
    }

    @Override
    @Cacheable(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageFunctionVO> getCacheManageFunctionList() {
        return cacheDataDAO.selectManageFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public void clearCacheManageFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageFunctionList()");
    }

    @Override
    @Cacheable(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageRoleFunctionVO> getCacheManageRoleFunctionList() {
        return cacheDataDAO.selectManageRoleFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public void clearCacheManageRoleFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageRoleFunctionList()");
    }
}
