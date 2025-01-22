package com.cjw.reactivecommunityproject.web.system.role_management.model.entity;

import lombok.Builder;

@Builder
public record SystemRoleManagementInsertEntity(
        Long uid,
        String name,
        String userUid
) {
}
