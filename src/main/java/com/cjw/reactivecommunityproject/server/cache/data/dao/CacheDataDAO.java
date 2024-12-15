package com.cjw.reactivecommunityproject.server.cache.data.dao;

import com.cjw.reactivecommunityproject.server.cache.data.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CacheDataDAO {
    List<CacheDataCommonRegionVO> selectCommonRegionList();

    List<CacheDataCommonEnvCodeVO> selectCommonEnvCodeList();

    List<CacheDataCommonLanguageCodeVO> selectCommonLanguageCodeList();

    List<CacheDataCommonLanguageGbCodeVO> selectCommonLanguageGbCodeList(@Param("lang") String lang);

    List<CacheDataManageResourceVO> selectManageResourceList();

    List<CacheDataManageFunctionVO> selectManageFunctionList();

    List<CacheDataManageRoleFunctionVO> selectManageRoleFunctionList();
}
