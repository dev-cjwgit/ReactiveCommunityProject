package com.cjw.reactivecommunityproject.server.cache.data.service;

import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleResourceVO;
import java.util.List;

public interface CacheDataService {
    List<CacheDataCommonRegionVO> getCacheCommonRegionList();
    void clearCacheCommonRegionList();

    List<CacheDataCommonEnvCodeVO> getCacheCommonEnvCodeList();
    void clearCacheCommonEnvCodeList();

    List<CacheDataCommonLanguageCodeVO> getCacheCommonLanguageCodeList();
    void clearCacheCommonLanguageCodeList();

    List<CacheDataCommonLanguageGbCodeVO> getCacheCommonLanguageGbCodeList(String lang);
    void clearCacheCommonLanguageGbCodeList();

    List<CacheDataManageResourceVO> getCacheManageResourceList();
    void clearCacheManageResourceList();

    List<CacheDataManageFunctionVO> getCacheManageFunctionList();
    void clearCacheManageFunctionList();

    List<CacheDataManageRoleFunctionVO> getCacheManageRoleFunctionList();
    void clearCacheManageRoleFunctionList();

    List<CacheDataManageRoleResourceVO> getCacheManageRoleResourceList();
    void clearCacheManageRoleResourceList();
}
