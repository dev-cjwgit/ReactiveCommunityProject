package com.cjw.reactivecommunityproject.server.cache.info.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CacheInfoCustomRoleFunctionVO implements CacheInfoDataUpdatable {
    private int roleUid;
    private long functionUid;
    private CommonEnabledEnum enabled;
    private String functionName;
    private RcManageFunctionTypeEnum functionType;
    private ZonedDateTime functionUpdatedUtcAt;

    @Override
    public ZonedDateTime getUpdatedUtcAt() {
        return this.functionUpdatedUtcAt;
    }
}




