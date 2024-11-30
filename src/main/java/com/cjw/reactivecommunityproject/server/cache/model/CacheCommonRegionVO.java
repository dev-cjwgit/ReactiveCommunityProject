package com.cjw.reactivecommunityproject.server.cache.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

import java.time.ZonedDateTime;

public record CacheCommonRegionVO(
        String region,
        CommonEnabledEnum enabled,
        ZonedDateTime updatedUtcAt
) {
}
