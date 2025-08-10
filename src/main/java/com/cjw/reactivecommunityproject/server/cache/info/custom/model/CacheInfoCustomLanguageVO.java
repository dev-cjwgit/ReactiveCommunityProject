package com.cjw.reactivecommunityproject.server.cache.info.custom.model;

import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CacheInfoCustomLanguageVO implements CacheInfoDataUpdatable {
    private String path;
    private String code;
    private String value;
    private String lang;
    private ZonedDateTime updatedUtcAt;
}




