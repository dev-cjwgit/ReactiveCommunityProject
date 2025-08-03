package com.cjw.reactivecommunityproject.server.cache.reset.model;

import com.cjw.reactivecommunityproject.server.cache.reset.interfaces.CacheResetTableNaming;

public enum CacheResetCustomTableEnum implements CacheResetTableNaming {
    COMMON_ENV_CODE
    , COMMON_ENV_CODE_BY_CATEGORY_LIST
    , COMMON_LANGUAGE_LIST
    , MANAGE_ROLE_FUNCTION_LIST
    , MANAGE_ROLE_RESOURCE_LIST;

    @Override
    public String getTableName() {
        return this.name();
    }
}
