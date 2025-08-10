package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.server.cache.info.data.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataCommonRegionVO implements CacheInfoDataUpdatable {
    private String region;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
