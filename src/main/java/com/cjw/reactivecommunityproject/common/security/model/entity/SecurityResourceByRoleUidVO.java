package com.cjw.reactivecommunityproject.common.security.model.entity;

import com.cjw.reactivecommunityproject.common.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.model.entity.RcResourceMethodEnum;

import java.time.ZonedDateTime;

public record SecurityResourceByRoleUidVO(
        Long uid,
        RcResourceMethodEnum method,
        String urlPattern,
        String description,
        CommonEnabledEnum enabled,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
