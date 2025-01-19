package com.cjw.reactivecommunityproject.web.system.resource_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;

import java.time.ZonedDateTime;

public record SysResourceManagementListEntity(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
