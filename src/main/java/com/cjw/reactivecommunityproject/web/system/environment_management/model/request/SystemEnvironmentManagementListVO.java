package com.cjw.reactivecommunityproject.web.system.environment_management.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record SystemEnvironmentManagementListVO(
        String id
        , String type
        , String value
        , String description
        , String category
        , ZonedDateTime startDate
        , ZonedDateTime endDate
) {
}
