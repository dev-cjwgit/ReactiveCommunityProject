package com.cjw.reactivecommunityproject.web.system.role_management.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record SystemRoleManagementListVO(
        Long uid,
        String name,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
