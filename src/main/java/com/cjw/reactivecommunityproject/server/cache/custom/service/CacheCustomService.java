package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;

import java.util.List;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCommonCustomEnvCode(String path, String code);

    List<CacheCustomEnvCodeVO> getCommonCustomEnvCodeByCategory(String category);
}
