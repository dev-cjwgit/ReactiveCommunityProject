package com.cjw.reactivecommunityproject.server.cache.data.model;

import java.time.ZonedDateTime;

public record CacheDataCommonLanguageCodeVO(
        String path,
        String code,
        String value,
        ZonedDateTime updatedUtcAt
) {
}
