package com.cjw.reactivecommunityproject.server.cache.model;

import java.time.ZonedDateTime;

public record CacheCommonEnvCodeVO(
        String path,
        String code,
        Integer order,
        String value,
        String category,
        ZonedDateTime updatedUtcAt
) {
}
