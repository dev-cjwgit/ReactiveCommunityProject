package com.cjw.reactivecommunityproject.server.cache.load.model;

import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonTypeEnum;
import java.util.List;

public record CacheLoadVO(
        CacheCommonTypeEnum type
        , List<CacheLoadParameterTableVO> table
) {
}
