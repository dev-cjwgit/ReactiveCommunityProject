package com.cjw.reactivecommunityproject.web.system.function_management.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

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
