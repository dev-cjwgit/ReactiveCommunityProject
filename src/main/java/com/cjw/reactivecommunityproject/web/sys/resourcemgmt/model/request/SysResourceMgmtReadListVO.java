package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SysResourceMgmtReadListVO(
        String method,
        String urlPattern,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
