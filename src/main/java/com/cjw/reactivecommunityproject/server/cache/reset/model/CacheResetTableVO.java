package com.cjw.reactivecommunityproject.server.cache.reset.model;

import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.common.interfaces.CacheCommonTableNaming;
import lombok.Builder;

@Builder
public record CacheResetTableVO(
        CacheCommonTypeEnum type
        , CacheCommonTableNaming table
) {
}
