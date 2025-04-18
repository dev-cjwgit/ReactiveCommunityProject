package com.cjw.reactivecommunityproject.server.cache.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CacheCustomRoleResourceVO {
    private Integer roleUid;
    private Long resourceUid;
    private CommonEnabledEnum enabled;
    private RcManageResourceMethodEnum method;
    private String urlPattern;
    private ZonedDateTime updatedUtcAt;
}




