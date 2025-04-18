package com.cjw.reactivecommunityproject.web.system.function_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import java.time.ZonedDateTime;

public record SystemFunctionManagementDetailEntity(
        Long uid,
        String name,
        RcManageFunctionTypeEnum type,
        String description,
        CommonEnabledEnum enabled,
        String createdUserUid,
        ZonedDateTime createdUtcAt,
        String updatedUserUid,
        ZonedDateTime updatedUtcAt
) {
}
