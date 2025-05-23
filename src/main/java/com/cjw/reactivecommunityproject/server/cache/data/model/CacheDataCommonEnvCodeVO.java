package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheDataCommonEnvCodeVO {
    private String id;
    private RcCommonEnvCodeTypeEnum type;
    private String value;
    private Integer order;
    private String category;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
