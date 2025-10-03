package com.cjw.reactivecommunityproject.web.board.bbs.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;

public record BoardBbsListEntity(
        Long uid
        , String path
        , String name
        , CommonEnabledEnum enabled
) {
}
