package com.cjw.reactivecommunityproject.web.auth.model.entity;

import lombok.Builder;

@Builder
public record AuthLoginEntity(
        String userUid
        , String refreshToken
) {
}
