package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheDataCommonRegionVO {
    private String region;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
