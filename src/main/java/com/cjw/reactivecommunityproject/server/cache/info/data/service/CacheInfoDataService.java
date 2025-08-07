package com.cjw.reactivecommunityproject.server.cache.info.data.service;

import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleResourceVO;
import java.util.List;

public interface CacheInfoDataService {
    List<CacheInfoDataCommonRegionVO> getCommonRegionList();
    void clearCommonRegionList();

    List<CacheInfoDataCommonEnvCodeVO> getCommonEnvCodeList();
    void clearCommonEnvCodeList();

    List<CacheInfoDataCommonLanguageCodeVO> getCommonLanguageCodeList();
    void clearCommonLanguageCodeList();

    List<CacheInfoDataCommonLanguageGbCodeVO> getCommonLanguageGbCodeList(String lang);
    void clearCommonLanguageGbCodeList();

    List<CacheInfoDataManageResourceVO> getManageResourceList();
    void clearManageResourceList();

    List<CacheInfoDataManageFunctionVO> getManageFunctionList();
    void clearManageFunctionList();

    List<CacheInfoDataManageRoleFunctionVO> getManageRoleFunctionList();
    void clearManageRoleFunctionList();

    List<CacheInfoDataManageRoleResourceVO> getManageRoleResourceList();
    void clearManageRoleResourceList();
}
