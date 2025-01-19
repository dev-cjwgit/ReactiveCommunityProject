package com.cjw.reactivecommunityproject.web.system.environment_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;

import java.time.ZonedDateTime;

public record SystemEnvironmentManagementListEntity(
        String id,
        RcCommonEnvCodeTypeEnum type,
        String value,
        String category,
        Integer order,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
