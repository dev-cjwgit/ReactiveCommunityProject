package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@ToString
public class CacheDataManageResourceVO {
    private Long uid;
    private RcManageResourceMethodEnum method;
    private String urlPattern;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
