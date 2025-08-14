package com.cjw.reactivecommunityproject.server.cache.info.data.mapper;

import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.info.data.model.CacheInfoDataManageRoleResourceVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CacheInfoDataMapper {
    List<CacheInfoDataCommonRegionVO> selectCommonRegionList();

    List<CacheInfoDataCommonEnvCodeVO> selectCommonEnvCodeList();

    List<CacheInfoDataCommonLanguageCodeVO> selectCommonLanguageCodeList();

    List<CacheInfoDataCommonLanguageGbCodeVO> selectCommonLanguageGbCodeList();

    List<CacheInfoDataManageResourceVO> selectManageResourceList();

    List<CacheInfoDataManageFunctionVO> selectManageFunctionList();

    List<CacheInfoDataManageRoleFunctionVO> selectManageRoleFunctionList();

    List<CacheInfoDataManageRoleResourceVO> selectManageRoleResourceList();
}
