package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleResourceVO;

import java.util.List;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCustomCommonEnvCode(String envId);
    void clearCustomCommonEnvCode();

    List<CacheCustomEnvCodeVO> getCustomCommonEnvCodeByCategoryList(String category);
    void clearCustomCommonEnvCodeByCategoryList();

    List<CacheCustomLanguageVO> getCustomCommonLangaugeList(String path, String lang);
    void clearCustomCommonLanguageList();

    List<CacheCustomRoleFunctionVO> getCustomManageRoleFunctionList(Integer roleUid);
    void clearCustomManageRoleFunctionList();

    List<CacheCustomRoleResourceVO> getCustomManageRoleResourceList(Integer roleUid);
    void clearCustomManageRoleResourceList();
}
