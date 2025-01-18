package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;

import java.time.ZonedDateTime;

public record SysResourceManagementListVO(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
