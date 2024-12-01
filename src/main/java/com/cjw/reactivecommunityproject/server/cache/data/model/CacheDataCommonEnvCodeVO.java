package com.cjw.reactivecommunityproject.server.cache.data.model;

import java.time.ZonedDateTime;

public record CacheDataCommonEnvCodeVO(
        String path,
        String code,
        Integer order,
        String value,
        String category,
        ZonedDateTime updatedUtcAt
) {
}
