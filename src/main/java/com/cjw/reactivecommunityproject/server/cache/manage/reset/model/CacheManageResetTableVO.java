package com.cjw.reactivecommunityproject.server.cache.manage.reset.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import com.cjw.reactivecommunityproject.server.cache.manage.common.interfaces.CacheManageCommonTableNaming;
import lombok.Builder;

@Builder
public record CacheManageResetTableVO(
        CacheManageCommonInfoTypeEnum type
        , CacheManageCommonTableNaming table
) {
}
