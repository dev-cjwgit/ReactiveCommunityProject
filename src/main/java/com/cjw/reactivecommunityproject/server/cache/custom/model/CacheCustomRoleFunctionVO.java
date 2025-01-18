package com.cjw.reactivecommunityproject.server.cache.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@Builder
@ToString
public class CacheCustomRoleFunctionVO {
    private Integer roleUid;
    private Long functionUid;
    private CommonEnabledEnum enabled;
    private String name;
    private RcManageFunctionTypeEnum type;
    private ZonedDateTime updatedUtcAt;
}



