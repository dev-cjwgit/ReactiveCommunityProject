package com.cjw.reactivecommunityproject.server.cache.reset.model;

import com.cjw.reactivecommunityproject.server.cache.common.model.CacheCommonTypeEnum;
import java.util.List;

public record CacheResetVO(
        CacheCommonTypeEnum type
        , List<String> table
) {
}
