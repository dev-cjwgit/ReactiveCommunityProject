package com.cjw.reactivecommunityproject.server.cache.model;

import java.time.ZonedDateTime;

public record CacheCommonLanguageCodeVO(
        String path,
        String code,
        String value,
        ZonedDateTime updatedUtcAt
) {
}
