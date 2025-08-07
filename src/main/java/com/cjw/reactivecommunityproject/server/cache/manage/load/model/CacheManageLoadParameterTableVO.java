package com.cjw.reactivecommunityproject.server.cache.manage.load.model;

import java.util.List;
import lombok.Builder;

@Builder
public record CacheManageLoadParameterTableVO(
        String tableName
        , List<Object> parameters
) {
}
