package com.cjw.reactivecommunityproject.common.security.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

public record SecurityRoleResourceMapping(
        Integer roleUid,
        Long resourceUid,
        CommonEnabledEnum enabled
) {
}
