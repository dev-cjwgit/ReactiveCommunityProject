package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.data.dao.CacheDataDAO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.service.CacheDataService;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
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
    @Cacheable(value = "getCacheCommonRegionList", cacheManager = "redisCacheManager")
    public List<CacheDataCommonRegionVO> getCacheCommonRegionList() {
        return cacheDataDAO.selectCommonRegionList();
    }

    @Override
    @Cacheable(value = "getCacheCommonEnvCodeList", cacheManager = "redisCacheManager")
    public List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList() {
        return cacheDataDAO.selectCommonEnvCodeList();
    }

    @Override
    @Cacheable(value = "getCacheCommonLanguageCodeList", cacheManager = "redisCacheManager")
    public List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList() {
        return cacheDataDAO.selectCommonLanguageCodeList();
    }

    @Override
    @Cacheable(value = "getCacheManageResourceList", cacheManager = "redisCacheManager")
    public List<CacheDataManageResourceVO> getCacheManageResourceList() {
        return cacheDataDAO.selectManageResourceList();
    }
}
