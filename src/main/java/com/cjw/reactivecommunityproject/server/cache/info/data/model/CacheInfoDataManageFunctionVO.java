package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataManageFunctionVO {
    private Long uid;
    private String name;
    private RcManageFunctionTypeEnum type;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
