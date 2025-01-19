package com.cjw.reactivecommunityproject.web.system.resource_management.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SystemResourceManagementListVO(
        String method,
        String urlPattern,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}