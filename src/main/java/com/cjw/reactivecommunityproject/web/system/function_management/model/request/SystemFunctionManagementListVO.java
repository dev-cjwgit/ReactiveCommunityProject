package com.cjw.reactivecommunityproject.web.system.function_management.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SystemFunctionManagementListVO(
        Long uid,
        String name,
        String type,
        String description,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
