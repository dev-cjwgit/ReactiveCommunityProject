package com.cjw.reactivecommunityproject.server.cache.manage.load.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.interfaces.CacheManageCommonTableNaming;
import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonTypeEnum;
import java.util.List;
import lombok.Builder;

@Builder
public record CacheManageLoadTableVO(
        CacheManageCommonTypeEnum type
        , CacheManageCommonTableNaming table
        , List<Object> parameters
) {
}
