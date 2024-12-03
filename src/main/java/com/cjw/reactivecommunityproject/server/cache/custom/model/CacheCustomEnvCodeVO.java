package com.cjw.reactivecommunityproject.server.cache.custom.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Builder
@ToString
public class CacheCustomEnvCodeVO {
    private String path;
    private String code;
    private String value;
    private Integer order;
    private String category;
    private ZonedDateTime updatedUtcAt;
}




