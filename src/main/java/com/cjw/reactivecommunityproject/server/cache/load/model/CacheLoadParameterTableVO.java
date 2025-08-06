package com.cjw.reactivecommunityproject.server.cache.load.model;

import java.util.List;
import lombok.Builder;

@Builder
public record CacheLoadParameterTableVO(
        String tableName
        , List<Object> parameters
) {
}
