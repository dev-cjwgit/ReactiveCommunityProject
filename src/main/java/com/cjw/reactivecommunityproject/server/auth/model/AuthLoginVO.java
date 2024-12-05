package com.cjw.reactivecommunityproject.server.auth.model;

import lombok.Builder;

@Builder
public record AuthLoginVO(
        String userUid,
        String refreshToken
) {
}
