package com.cjw.reactivecommunityproject.web.log.exception.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record LogExceptionListVO(
        ZonedDateTime startDate,
        ZonedDateTime endDate
) {
}
