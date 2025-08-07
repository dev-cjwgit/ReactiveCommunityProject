package com.cjw.reactivecommunityproject.server.cache.manage.reset.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonTypeEnum;
import java.util.List;

public record CacheManageResetVO(
        CacheManageCommonTypeEnum type
        , List<String> table
) {
}
