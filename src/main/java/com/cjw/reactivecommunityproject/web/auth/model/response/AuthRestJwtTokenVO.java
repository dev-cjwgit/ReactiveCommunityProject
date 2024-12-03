package com.cjw.reactivecommunityproject.web.auth.model.response;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthRestJwtTokenVO(
        String accessToken,
        String refreshToken
) {
}
