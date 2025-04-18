package com.cjw.reactivecommunityproject.web.system.role_management.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record SystemRoleManagementListVO(
        Long uid,
        String name,
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
