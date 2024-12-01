package com.cjw.reactivecommunityproject.server.cache.data.service.impl;

import com.cjw.reactivecommunityproject.server.cache.data.dao.CacheDataDAO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.service.RedisCacheDataService;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheDataServiceImpl implements RedisCacheDataService {
    private final CacheDataDAO cacheDataDAO;

    @Override
    @Cacheable(value = "getCacheCommonRegionList")
    public List<CacheDataCommonRegionVO> getCacheCommonRegionList() {
        return cacheDataDAO.selectCommonRegionList();
    }

    @Override
    @Cacheable(value = "getCacheCommonEnvCodeList")
    public List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList() {
        return cacheDataDAO.selectCommonEnvCodeList();
    }

    @Override
    @Cacheable(value = "getCacheCommonLanguageCodeList")
    public List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList() {
        return cacheDataDAO.selectCommonLanguageCodeList();
    }

    @Override
    @Cacheable(value = "getCacheManageResourceList")
    public List<CacheDataManageResourceVO> getCacheManageResourceList() {
        return cacheDataDAO.selectManageResourceList();
    }
}
