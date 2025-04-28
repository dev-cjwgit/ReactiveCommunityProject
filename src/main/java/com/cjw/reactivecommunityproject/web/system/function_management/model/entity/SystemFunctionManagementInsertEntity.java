package com.cjw.reactivecommunityproject.web.system.function_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import lombok.Builder;

@Builder
public record SystemFunctionManagementInsertEntity(
        String name
        , RcManageFunctionTypeEnum type
        , String description
        , String userUid
) {
}
