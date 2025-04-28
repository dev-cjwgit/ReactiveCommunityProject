package com.cjw.reactivecommunityproject.web.system.resource_management.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import lombok.Builder;

@Builder
public record SystemResourceManagementInsertEntity(
        RcManageResourceMethodEnum method
        , String urlPattern
        , String description
        , String userUid
) {
}
