package com.cjw.reactivecommunityproject.server.cache.data.model;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@ToString
public class CacheDataCommonLanguageCodeVO {
    private String path;
    private String code;
    private String value;
    private ZonedDateTime updatedUtcAt;
}
