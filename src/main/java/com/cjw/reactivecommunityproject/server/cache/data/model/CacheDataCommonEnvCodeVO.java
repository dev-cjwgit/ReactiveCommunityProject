package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@ToString
public class CacheDataCommonEnvCodeVO {
    private String id;
    private RcCommonEnvCodeTypeEnum type;
    private String value;
    private Integer order;
    private String category;
    private ZonedDateTime updatedUtcAt;
}
