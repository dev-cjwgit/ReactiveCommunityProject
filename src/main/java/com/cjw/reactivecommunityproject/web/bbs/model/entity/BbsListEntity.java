package com.cjw.reactivecommunityproject.web.bbs.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

public record BbsListEntity(
        Long uid
        , String path
        , String name
        , CommonEnabledEnum enabled
) {
}
