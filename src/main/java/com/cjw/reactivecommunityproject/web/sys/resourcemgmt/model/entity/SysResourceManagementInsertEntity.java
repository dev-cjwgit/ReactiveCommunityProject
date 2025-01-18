package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import lombok.Builder;

@Builder
public record SysResourceManagementInsertEntity(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String description,
        String userUid
) {
}
