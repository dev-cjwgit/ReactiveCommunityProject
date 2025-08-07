package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataCommonLanguageGbCodeVO {
    private String path;
    private String code;
    private String lang;
    private String value;
    private ZonedDateTime updatedUtcAt;
}
