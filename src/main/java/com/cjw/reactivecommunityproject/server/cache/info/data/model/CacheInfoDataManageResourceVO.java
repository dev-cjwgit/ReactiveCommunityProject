package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataManageResourceVO {
    private Long uid;
    private RcManageResourceMethodEnum method;
    private String urlPattern;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
