package com.cjw.reactivecommunityproject.web.system.environment_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import lombok.Builder;

@Builder
public record SystemEnvironmentManagementModifyEntity(
        String id,
        RcCommonEnvCodeTypeEnum type,
        String value,
        String category,
        Integer order,
        CommonEnabledEnum enabled,
        String userUid
) {
}
