package com.cjw.reactivecommunityproject.server.cache.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;

import java.time.ZonedDateTime;

public record CacheManageResourceVO(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        CommonEnabledEnum enabled,
        ZonedDateTime updatedUtcAt
) {
}
