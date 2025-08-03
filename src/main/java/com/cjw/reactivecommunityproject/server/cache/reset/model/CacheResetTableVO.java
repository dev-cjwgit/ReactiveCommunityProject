package com.cjw.reactivecommunityproject.server.cache.reset.model;

import com.cjw.reactivecommunityproject.server.cache.reset.interfaces.CacheResetTableNaming;
import lombok.Builder;

@Builder
public record CacheResetTableVO(
        CacheResetTypeEnum type
        , CacheResetTableNaming table
) {
}
