package com.cjw.reactivecommunityproject.server.batch.cache.data.model;

import lombok.Builder;

@Builder
public record BatchCacheDataVO(
        String cacheMethodName
        , Object dbData
        , Object cacheData
) {
}
