package com.cjw.reactivecommunityproject.web.system.role_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import java.time.ZonedDateTime;

public record SystemRoleManagementDetailEntity(
        Integer uid,
        String name,
        CommonEnabledEnum enabled,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
