package com.cjw.reactivecommunityproject.server.cache.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheDataManageRoleFunctionVO {
    private Integer roleUid;
    private Long functionUid;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
