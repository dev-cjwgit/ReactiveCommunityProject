package com.cjw.reactivecommunityproject.web.system.resource_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import lombok.Builder;

@Builder
public record SysResourceManagementModifyEntity(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String description,
        String userUid,
        CommonEnabledEnum enabled
) {
}
