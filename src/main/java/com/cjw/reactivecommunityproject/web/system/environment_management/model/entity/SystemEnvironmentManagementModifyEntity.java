package com.cjw.reactivecommunityproject.web.system.environment_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import lombok.Builder;

@Builder
public record SystemEnvironmentManagementModifyEntity(
        String region
        , String id
        , RcCommonEnvCodeTypeEnum type
        , String value
        , String description
        , String category
        , Integer order
        , CommonEnabledEnum enabled
        , String userUid
) {
}
