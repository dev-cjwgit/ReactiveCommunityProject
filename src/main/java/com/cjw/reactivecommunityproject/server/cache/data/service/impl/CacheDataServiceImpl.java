package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.common.spring.config.properties.RcProperties;
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
    private final RcProperties rcProperties;
    private final CacheDataMapper cacheDataMapper;

    @Override
    @Cacheable(value = "rc_common_region", cacheManager = "redisCacheManager")
    public List<CacheDataCommonRegionVO> getCommonRegionList() {
        return cacheDataMapper.selectCommonRegionList();
    }

    @Override
    @CacheEvict(value = "rc_common_region", cacheManager = "redisCacheManager")
    public void clearCommonRegionList() {
        log.info("CacheDataServiceImpl.clearCacheCommonRegionList()");
    }

    @Override
    @Cacheable(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonEnvCodeVO> getCommonEnvCodeList() {
        return cacheDataMapper.selectCommonEnvCodeList(rcProperties.config().defaultRegion());
    }

    @Override
    @CacheEvict(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public void clearCommonEnvCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonEnvCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageCodeVO> getCommonLanguageCodeList() {
        return cacheDataMapper.selectCommonLanguageCodeList();
    }

    @Override
    @CacheEvict(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public void clearCommonLanguageCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageCodeList()");
    }

    @Override
    @Cacheable(value = "rc_common_language_gb_code", key = "'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageGbCodeVO> getCommonLanguageGbCodeList(String lang) {
        return cacheDataMapper.selectCommonLanguageGbCodeList(lang);
    }

    @Override
    @CacheEvict(value = "rc_common_language_gb_code", allEntries = true, cacheManager = "redisCacheManager")
    public void clearCommonLanguageGbCodeList() {
        log.info("CacheDataServiceImpl.clearCacheCommonLanguageGbCodeList()");
    }

    @Override
    @Cacheable(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageResourceVO> getManageResourceList() {
        return cacheDataMapper.selectManageResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public void clearManageResourceList() {
        log.info("CacheDataServiceImpl.clearCacheManageResourceList()");
    }

    @Override
    @Cacheable(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageFunctionVO> getManageFunctionList() {
        return cacheDataMapper.selectManageFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_function", cacheManager = "redisCacheManager")
    public void clearManageFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageFunctionList()");
    }

    @Override
    @Cacheable(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public List<CacheDataManageRoleFunctionVO> getManageRoleFunctionList() {
        return cacheDataMapper.selectManageRoleFunctionList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_function", cacheManager = "redisCacheManager")
    public void clearManageRoleFunctionList() {
        log.info("CacheDataServiceImpl.clearCacheManageRoleFunctionList()");
    }

    @Override
    @Cacheable(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageRoleResourceVO> getManageRoleResourceList() {
        return cacheDataMapper.selectManageRoleResourceList();
    }

    @Override
    @CacheEvict(value = "rc_manage_role_resource", cacheManager = "redisCacheManager")
    public void clearManageRoleResourceList() {
        log.info("CacheDataServiceImpl.clearCacheManageRoleFunctionList()");
    }
}
