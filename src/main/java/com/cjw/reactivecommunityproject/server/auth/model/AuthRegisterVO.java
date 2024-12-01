package com.cjw.reactivecommunityproject.server.auth.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record AuthRegisterVO(
        String uid,
        Integer roleUid,
        String email,
        String pw,
        String name,
        String nickname,
        String joinedRegion
) implements Serializable {
}
