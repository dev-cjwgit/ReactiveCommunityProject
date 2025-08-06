package com.cjw.reactivecommunityproject.server.cache.custom.service;

import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.custom.model.CacheCustomRoleResourceVO;
import java.util.List;

public interface CacheCustomService {
    CacheCustomEnvCodeVO getCommonEnvCode(String envId);
    void clearCommonEnvCode();

    List<CacheCustomEnvCodeVO> getCommonEnvCodeByCategoryList(String category);
    void clearCommonEnvCodeByCategoryList();

    List<CacheCustomLanguageVO> getCommonLanguageList(String path, String lang);
    void clearCommonLanguageList();

    List<CacheCustomRoleFunctionVO> getManageRoleFunctionList(Integer roleUid);
    void clearManageRoleFunctionList();

    List<CacheCustomRoleResourceVO> getManageRoleResourceList(Integer roleUid);
    void clearManageRoleResourceList();
}
