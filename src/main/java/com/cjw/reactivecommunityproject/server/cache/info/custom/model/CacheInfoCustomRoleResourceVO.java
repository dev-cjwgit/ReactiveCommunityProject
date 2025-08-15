package com.cjw.reactivecommunityproject.server.cache.info.custom.model;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.server.cache.info.common.interfaces.CacheInfoDataUpdatable;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CacheInfoCustomRoleResourceVO implements CacheInfoDataUpdatable {
    private int roleUid;
    private long resourceUid;
    private CommonEnabledEnum enabled;
    private RcManageResourceMethodEnum method;
    private String urlPattern;
    private ZonedDateTime roleResourceUpdatedUtcAt;

    @Override
    public ZonedDateTime getUpdatedUtcAt() {
        return this.roleResourceUpdatedUtcAt;
    }
}