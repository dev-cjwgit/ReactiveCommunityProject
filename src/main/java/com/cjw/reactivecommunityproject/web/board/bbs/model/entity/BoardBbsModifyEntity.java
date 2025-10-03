package com.cjw.reactivecommunityproject.web.board.bbs.model.entity;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import lombok.Builder;

@Builder
public record BoardBbsModifyEntity(
        Long uid
        , String path
        , String name
        , String description
        , String userUid
        , CommonEnabledEnum enabled
) {
}
