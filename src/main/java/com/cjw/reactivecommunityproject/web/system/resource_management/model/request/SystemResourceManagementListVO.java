package com.cjw.reactivecommunityproject.web.system.resource_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record SystemResourceManagementListVO(
        Long uid
        , String method
        , String urlPattern
        , String description
        , CommonEnabledEnum enabled
        , ZonedDateTime startDate
        , ZonedDateTime endDate
) {
}
