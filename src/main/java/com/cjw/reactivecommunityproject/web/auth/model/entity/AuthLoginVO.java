package com.cjw.reactivecommunityproject.web.auth.model.entity;

import lombok.Builder;

@Builder
public record AuthLoginVO(
        String userUid,
        String refreshToken
) {
}
