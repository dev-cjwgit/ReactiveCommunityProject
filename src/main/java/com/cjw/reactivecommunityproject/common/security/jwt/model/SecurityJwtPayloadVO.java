package com.cjw.reactivecommunityproject.common.security.jwt.model;

import lombok.Builder;

@Builder
public record SecurityJwtPayloadVO(
        String sub
        , String role
        , Long lat
        , Long exp
) {
}
