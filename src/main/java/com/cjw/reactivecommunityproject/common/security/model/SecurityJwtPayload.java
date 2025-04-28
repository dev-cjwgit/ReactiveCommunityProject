package com.cjw.reactivecommunityproject.common.security.model;

import lombok.Builder;

@Builder
public record SecurityJwtPayload(
        String sub
        , String role
        , Long lat
        , Long exp
) {
}
