package com.cjw.reactivecommunityproject.web.auth.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcUserStateEnum;

import java.time.ZonedDateTime;

public record AuthRestRcUserVO(
        String uid,
        Integer roleUid,
        String email,
        String pw,
        RcUserStateEnum state,
        String acceptUserUid,
        String name,
        String nickname,
        String joinedRegion,
        CommonEnabledEnum enabled,
        ZonedDateTime createdUtcAt,
        ZonedDateTime updatedUtcAt
) {
}