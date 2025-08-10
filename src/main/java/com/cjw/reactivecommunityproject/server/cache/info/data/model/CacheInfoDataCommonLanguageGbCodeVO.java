package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataCommonLanguageGbCodeVO implements CacheInfoDataUpdatable {
    private String path;
    private String code;
    private String lang;
    private String value;
    private ZonedDateTime updatedUtcAt;
}
