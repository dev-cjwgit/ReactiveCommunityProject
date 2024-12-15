package com.cjw.reactivecommunityproject.server.sys.resourcemgmt.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import lombok.Builder;

@Builder
public record SysResourceMgmtUpdateVO(
        Long uid,
        RcManageResourceMethodEnum method,
        String urlPattern,
        String description,
        String userUid,
        CommonEnabledEnum enabled
) {
}
