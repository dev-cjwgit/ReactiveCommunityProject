package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleResourceVO;

import java.util.List;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCommonCustomEnvCode(String path, String code);
    void clearCommonCustomEnvCode();

    List<CacheCustomEnvCodeVO> getCommonCustomEnvCodeByCategoryList(String category);
    void clearCommonCustomEnvCodeByCategoryList();

    List<CacheCustomLanguageVO> getCommonCustomLangaugeList(String path, String lang);
    void clearCommonCustomLanguageList();

    List<CacheCustomRoleFunctionVO> getCustomManageRoleFunctionList(Integer roleUid);
    void clearCustomManageRoleFunctionList();

    List<CacheCustomRoleResourceVO> getCustomManageRoleResourceList(Integer roleUid);
    void clearCustomManageRoleResourceList();
}
