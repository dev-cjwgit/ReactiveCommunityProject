package com.cjw.reactivecommunityproject.server.cache.data.mapper;

import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonEnvCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonLanguageGbCodeVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataCommonRegionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageResourceVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleFunctionVO;
import com.cjw.reactivecommunityproject.server.cache.data.model.CacheDataManageRoleResourceVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CacheDataMapper {
    List<CacheDataCommonRegionVO> selectCommonRegionList();

    List<CacheDataCommonEnvCodeVO> selectCommonEnvCodeList();

    List<CacheDataCommonLanguageCodeVO> selectCommonLanguageCodeList();

    List<CacheDataCommonLanguageGbCodeVO> selectCommonLanguageGbCodeList(@Param("lang") String lang);

    List<CacheDataManageResourceVO> selectManageResourceList();

    List<CacheDataManageFunctionVO> selectManageFunctionList();

    List<CacheDataManageRoleFunctionVO> selectManageRoleFunctionList();

    List<CacheDataManageRoleResourceVO> selectManageRoleResourceList();
}
