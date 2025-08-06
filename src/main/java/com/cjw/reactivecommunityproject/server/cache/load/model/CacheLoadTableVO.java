package com.cjw.reactivecommunityproject.server.cache.load.model;

import com.cjw.reactivecommunityproject.server.cache.common.interfaces.CacheCommonTableNaming;
import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonTypeEnum;
import java.util.List;
import lombok.Builder;

@Builder
public record CacheLoadTableVO(
        CacheCommonTypeEnum type
        , CacheCommonTableNaming table
        , List<Object> parameters
) {
}
