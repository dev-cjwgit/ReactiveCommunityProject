package com.cjw.reactivecommunityproject.server.cache.manage.load.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonTypeEnum;
import java.util.List;

public record CacheManageLoadVO(
        CacheManageCommonTypeEnum type
        , List<CacheManageLoadParameterTableVO> table
) {
}
