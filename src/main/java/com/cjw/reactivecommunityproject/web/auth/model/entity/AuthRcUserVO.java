package com.cjw.reactivecommunityproject.web.auth.model.entity;

import com.cjw.reactivecommunityproject.common.model.entity.CommonEnabled;
import com.cjw.reactivecommunityproject.common.model.entity.RcUserStateEnum;

import java.time.ZonedDateTime;

public record AuthRcUserVO(
        String uid,
        Integer roleUid,
        String email,
        String pw,
        RcUserStateEnum state,
        String acceptUserUid,
        String name,
        String nickname,
        String joinedRegion,
        CommonEnabled enabled,
        ZonedDateTime createdUtcAt,
        ZonedDateTime updatedUtcAt
) {
}
