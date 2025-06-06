package com.cjw.reactivecommunityproject.server.cache.custom.model;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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




