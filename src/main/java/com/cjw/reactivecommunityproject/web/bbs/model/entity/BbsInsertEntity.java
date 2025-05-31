package com.cjw.reactivecommunityproject.web.bbs.model.entity;

import lombok.Builder;

@Builder
public record BbsInsertEntity(
        String path
        , String name
        , String description
        , String userUid
) {
}
