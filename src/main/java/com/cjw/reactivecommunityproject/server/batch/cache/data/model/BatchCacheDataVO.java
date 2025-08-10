package com.cjw.reactivecommunityproject.server.batch.cache.data.model;

import lombok.Builder;

@Builder
public record BatchCacheDataVO(
        String tableName
        , Object dbData
        , Object cacheData
) {
}
