package com.cjw.reactivecommunityproject.web.system.role_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import lombok.Builder;

@Builder
public record SystemRoleManagementModifyEntity(
        Integer uid
        , String name
        , String description
        , CommonEnabledEnum enabled
        , String userUid
) {
}
