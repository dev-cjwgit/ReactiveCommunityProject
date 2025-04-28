package com.cjw.reactivecommunityproject.web.system.function_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import lombok.Builder;

@Builder
public record SystemFunctionManagementModifyEntity(
        Long uid
        , String name
        , RcManageFunctionTypeEnum type
        , String description
        , String userUid
        , CommonEnabledEnum enabled
) {
}
