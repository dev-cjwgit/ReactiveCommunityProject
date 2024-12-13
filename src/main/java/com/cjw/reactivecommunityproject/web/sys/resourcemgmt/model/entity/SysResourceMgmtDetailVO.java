package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;

import java.time.ZonedDateTime;

public record SysResourceMgmtDetailVO(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String description,
        CommonEnabledEnum enabled,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
