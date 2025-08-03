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
    List<CacheDataCommonRegionVO> getCommonRegionList();
    void clearCommonRegionList();

    List<CacheDataCommonEnvCodeVO> getCommonEnvCodeList();
    void clearCommonEnvCodeList();

    List<CacheDataCommonLanguageCodeVO> getCommonLanguageCodeList();
    void clearCommonLanguageCodeList();

    List<CacheDataCommonLanguageGbCodeVO> getCommonLanguageGbCodeList(String lang);
    void clearCommonLanguageGbCodeList();

    List<CacheDataManageResourceVO> getManageResourceList();
    void clearManageResourceList();

    List<CacheDataManageFunctionVO> getManageFunctionList();
    void clearManageFunctionList();

    List<CacheDataManageRoleFunctionVO> getManageRoleFunctionList();
    void clearManageRoleFunctionList();

    List<CacheDataManageRoleResourceVO> getManageRoleResourceList();
    void clearManageRoleResourceList();
}
