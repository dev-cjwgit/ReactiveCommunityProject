package com.cjw.reactivecommunityproject.server.cache.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CacheCustomEnvCodeVO {
    private String region;
    private String id;
    private RcCommonEnvCodeTypeEnum type;
    private String value;
    private String category;
    private Integer order;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}




