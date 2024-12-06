package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SysResourceMgmtSelectListVO(
        String method,
        String urlPattern,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
