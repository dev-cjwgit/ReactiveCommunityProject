package com.cjw.reactivecommunityproject.server.cache.custom.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Builder
@ToString
public class CacheCustomLanguageVO {
    private String path;
    private String code;
    private String value;
    private String lang;
    private ZonedDateTime updatedUtcAt;
}




