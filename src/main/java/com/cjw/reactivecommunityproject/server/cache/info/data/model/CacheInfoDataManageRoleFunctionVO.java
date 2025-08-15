package com.cjw.reactivecommunityproject.server.cache.info.data.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheInfoDataManageRoleFunctionVO implements CacheInfoDataUpdatable {
    private int roleUid;
    private long functionUid;
    private CommonEnabledEnum enabled;
    private ZonedDateTime updatedUtcAt;
}
