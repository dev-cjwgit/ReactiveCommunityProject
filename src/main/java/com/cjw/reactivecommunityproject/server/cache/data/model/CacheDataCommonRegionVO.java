package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

import java.time.ZonedDateTime;

public record CacheDataCommonRegionVO(
        String region,
        CommonEnabledEnum enabled,
        ZonedDateTime updatedUtcAt
) {
}
