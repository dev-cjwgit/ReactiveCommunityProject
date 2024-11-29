package com.cjw.reactivecommunityproject.common.model.response;

import lombok.Builder;

@Builder
public record RestResponseVO<T>(
        Boolean result,
        Long code,
        String message,
        T data
) {
}
