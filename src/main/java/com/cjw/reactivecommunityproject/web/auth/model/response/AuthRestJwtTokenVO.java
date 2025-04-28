package com.cjw.reactivecommunityproject.web.auth.model.response;

import lombok.Builder;

@Builder
public record AuthRestJwtTokenVO(
        String accessToken
        , String refreshToken
) {
}
