package com.cjw.reactivecommunityproject.web.auth.model.entity;

import lombok.Builder;

@Builder
public record AuthRegisterEntity(
        String uid,
        Integer roleUid,
        String email,
        String pw,
        String name,
        String nickname,
        String joinedRegion
) {
}
