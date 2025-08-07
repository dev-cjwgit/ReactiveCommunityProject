package com.cjw.reactivecommunityproject.server.cache.info.custom.service;

import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomLanguageVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.custom.model.CacheInfoCustomRoleResourceVO;
import java.util.List;

public interface CacheInfoCustomService {
    CacheInfoCustomEnvCodeVO getCommonEnvCode(String envId);
    void clearCommonEnvCode();

    List<CacheInfoCustomEnvCodeVO> getCommonEnvCodeByCategoryList(String category);
    void clearCommonEnvCodeByCategoryList();

    List<CacheInfoCustomLanguageVO> getCommonLanguageList(String path, String lang);
    void clearCommonLanguageList();

    List<CacheInfoCustomRoleFunctionVO> getManageRoleFunctionList(Integer roleUid);
    void clearManageRoleFunctionList();

    List<CacheInfoCustomRoleResourceVO> getManageRoleResourceList(Integer roleUid);
    void clearManageRoleResourceList();
}
