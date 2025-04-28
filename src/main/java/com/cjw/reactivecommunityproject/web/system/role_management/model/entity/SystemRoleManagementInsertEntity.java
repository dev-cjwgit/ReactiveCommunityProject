package com.cjw.reactivecommunityproject.web.system.role_management.model.entity;

import lombok.Builder;

@Builder
public record SystemRoleManagementInsertEntity(
        Integer uid,
        String name,
        String description,
        String userUid
) {
}
