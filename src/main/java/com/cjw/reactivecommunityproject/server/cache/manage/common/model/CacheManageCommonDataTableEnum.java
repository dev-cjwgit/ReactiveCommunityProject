package com.cjw.reactivecommunityproject.server.cache.manage.common.model;

import com.cjw.reactivecommunityproject.server.cache.manage.common.interfaces.CacheManageCommonTableNaming;

public enum CacheManageCommonDataTableEnum implements CacheManageCommonTableNaming {
    COMMON_REGION_LIST
    , COMMON_ENV_CODE_LIST
    , COMMON_LANGUAGE_CODE_LIST
    , COMMON_LANGUAGE_GB_CODE_LIST
    , MANAGE_RESOURCE_LIST
    , MANAGE_FUNCTION_LIST
    , MANAGE_ROLE_FUNCTION_LIST
    , MANAGE_ROLE_RESOURCE_LIST;

    @Override
    public String getTableName() {
        return this.name();
    }
}
