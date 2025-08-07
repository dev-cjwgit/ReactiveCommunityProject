package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataCommonLanguageCodeVO {
    private String path;
    private String code;
    private String value;
    private ZonedDateTime updatedUtcAt;
}
