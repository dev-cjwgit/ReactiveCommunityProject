package com.cjw.reactivecommunityproject.web.system.resource_management.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record SystemResourceManagementListVO(
        Long uid,
        String method,
        String urlPattern,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
