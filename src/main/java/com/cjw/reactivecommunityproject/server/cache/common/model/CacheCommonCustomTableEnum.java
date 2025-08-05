package com.cjw.reactivecommunityproject.server.cache.common.model;

import com.cjw.reactivecommunityproject.server.cache.common.interfaces.CacheCommonTableNaming;

public enum CacheCommonCustomTableEnum implements CacheCommonTableNaming {
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
