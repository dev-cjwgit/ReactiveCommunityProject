package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.data.dao.CacheDataDAO;
import com.cjw.reactivecommunityproject.server.cache.data.model.*;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Cacheable(value = "rc_common_env_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList() {
        return cacheDataDAO.selectCommonEnvCodeList();
    }

    @Override
    @Cacheable(value = "rc_common_language_code", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList() {
        return cacheDataDAO.selectCommonLanguageCodeList();
    }

    @Override
    @Cacheable(value = "rc_common_language_gb_code", key = "'lang=' + #lang", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageGbCodeVO> getCacheCommonLanguageGbCodeList(String lang) {
        return cacheDataDAO.selectCommonLanguageGbCodeList(lang);
    }

    @Override
    @Cacheable(value = "rc_manage_resource", cacheManager = "redisCacheManager")
    public List<CacheDataManageResourceVO> getCacheManageResourceList() {
        return cacheDataDAO.selectManageResourceList();
    }
}
