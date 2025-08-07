package com.cjw.reactivecommunityproject.server.cache.manage.load.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import java.util.List;

public record CacheManageLoadVO(
        CacheManageCommonInfoTypeEnum type
        , List<CacheManageLoadParameterTableVO> table
) {
}
