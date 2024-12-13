package com.cjw.reactivecommunityproject.server.auth.model;

import lombok.Builder;

@Builder
public record AuthRegisterVO(
        String uid,
        Integer roleUid,
        String email,
        String pw,
        String name,
        String nickname,
        String joinedRegion
) {
}
