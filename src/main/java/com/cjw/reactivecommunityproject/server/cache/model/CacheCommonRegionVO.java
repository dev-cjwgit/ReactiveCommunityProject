package com.cjw.reactivecommunityproject.server.cache.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

import java.time.ZonedDateTime;

public record CacheCommonRegionVO(
        String region,
        String description,
        CommonEnabledEnum enabled,
        ZonedDateTime updatedUtcAt
) {
}
