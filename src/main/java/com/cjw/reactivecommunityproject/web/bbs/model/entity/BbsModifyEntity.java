package com.cjw.reactivecommunityproject.web.bbs.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import lombok.Builder;

@Builder
public record BbsModifyEntity(
        Long uid
        , String path
        , String name
        , String description
        , String userUid
        , CommonEnabledEnum enabled
) {
}
