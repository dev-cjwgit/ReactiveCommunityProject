package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;

import java.util.List;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCommonCustomEnvCode(String path, String code);

    List<CacheCustomEnvCodeVO> getCommonCustomEnvCodeByCategoryList(String category);

    List<CacheCustomLanguageVO> getCommonCustomLangaugeList(String path, String lang);
}
