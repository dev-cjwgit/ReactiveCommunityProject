package com.cjw.reactivecommunityproject.server.cache.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Builder
@ToString
public class CacheCustomEnvCodeVO {
    private String id;
    private RcCommonEnvCodeTypeEnum type;
    private String value;
    private String category;
    private Integer order;
    private ZonedDateTime updatedUtcAt;
}




