package com.cjw.reactivecommunityproject.web.auth.model.response;

import lombok.Builder;

@Builder
public record AuthJwtTokenVO(
        String accessToken,
        String refreshToken
) {
}
