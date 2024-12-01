package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCommonCustomEnvCode(String path, String code);
}
