package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@ToString
public class CacheDataCommonRegionVO implements Serializable {
    private String region;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
