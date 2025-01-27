package com.cjw.reactivecommunityproject.web.log.exception.model.request;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record LogExceptionListVO(
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
