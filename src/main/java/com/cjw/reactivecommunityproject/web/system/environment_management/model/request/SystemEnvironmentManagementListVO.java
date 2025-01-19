package com.cjw.reactivecommunityproject.web.system.environment_management.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SystemEnvironmentManagementListVO(
        String id,
        String type,
        String value,
        String category,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
