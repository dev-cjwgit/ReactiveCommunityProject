package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.data.mapper.CacheDataMapper;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleResourceVO;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheDataServiceImpl implements CacheDataService {
    private final CacheDataMapper cacheDataMapper;

    @Override
    @Cacheable(value = "rc_common_region", cacheManager = "redisCacheManager")
    public List<CacheDataCommonRegionVO> getCacheCommonRegionList() {
        return cacheDataMapper.selectCommonRegionList();
    }

    @Override
    @CacheEvict(value = "rc_common_region", cacheManager = "redisCacheManager")
    public void clearCacheCommonRegionList() {
        log.info("CacheDataServiceImpl.clearCacheCommonRegionList()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList() {
        return cacheDataMapper.selectCommonEnvCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public void clearCacheCommonEnvCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonEnvCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList() {
        return cacheDataMapper.selectCommonLanguageCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public void clearCacheCommonLanguageCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_gb_code", key = "'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageGbCodeVO> getCacheCommonLanguageGbCodeList(String lang) {
        return cacheDataMapper.selectCommonLanguageGbCodeList(lang);
    }

    @Override
    @CacheEvict(value = "rc_common_language_gb_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCacheCommonLanguageGbCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageGbCodeList()");
    }

    @Override
    @Cacheable(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageResourceVO> getCacheManageResourceList() {
        return cacheDataMapper.selectManageResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public void clearCacheManageResourceList() {
        log.info("CacheDataServiceImpl.clearCacheManageResourceList()");
    }

    @Override
    @Cacheable(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageFunctionVO> getCacheManageFunctionList() {
        return cacheDataMapper.selectManageFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public void clearCacheManageFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageFunctionList()");
    }

    @Override
    @Cacheable(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageRoleFunctionVO> getCacheManageRoleFunctionList() {
        return cacheDataMapper.selectManageRoleFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public void clearCacheManageRoleFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageRoleFunctionList()");
    }

    @Override
    @Cacheable(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageRoleResourceVO> getCacheManageRoleResourceList() {
        return cacheDataMapper.selectManageRoleResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public void clearCacheManageRoleResourceList() {
        log.info("CacheDataServiceImpl.clearCacheManageRoleFunctionList()");
    }
}
