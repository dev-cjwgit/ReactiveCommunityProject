package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@ToString
public class CacheDataManageRoleFunctionVO {
    private Integer roleUid;
    private Long functionUid;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
