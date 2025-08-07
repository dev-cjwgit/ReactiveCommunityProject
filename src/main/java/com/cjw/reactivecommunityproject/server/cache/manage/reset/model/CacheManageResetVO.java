package com.cjw.reactivecommunityproject.server.cache.manage.reset.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.model.CacheManageCommonInfoTypeEnum;
import java.util.List;

public record CacheManageResetVO(
        CacheManageCommonInfoTypeEnum type
        , List<String> table
) {
}
