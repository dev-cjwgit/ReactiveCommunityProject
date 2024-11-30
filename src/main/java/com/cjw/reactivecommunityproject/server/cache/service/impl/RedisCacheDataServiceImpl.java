package com.cjw.reactivecommunityproject.server.cache.service.impl;

import com.cjw.reactivecommunityproject.server.cache.dao.CacheDAO;
import com.cjw.reactivecommunityproject.server.cache.model.CacheCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.service.RedisCacheDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheDataServiceImpl implements RedisCacheDataService {
    private final CacheDAO cacheDAO;

    @Override
    @Cacheable(value = "getCacheCommonRegionList")
    public List<CacheCommonRegionVO> getCacheCommonRegionList() {
        return cacheDAO.selectCommonRegionList();
    }
}
