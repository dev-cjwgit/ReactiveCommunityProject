package com.cjw.reactivecommunityproject.server.cache.reset.model;

import java.util.List;

public record CacheResetVO(
        CacheResetTypeEnum type
        , List<String> table
) {
}
