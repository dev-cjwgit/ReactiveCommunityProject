package com.cjw.reactivecommunityproject.web.auth.model.response;

import lombok.Builder;

@Builder
public record AuthRestJwtAccessTokenVO(
        String accessToken
) {
}
