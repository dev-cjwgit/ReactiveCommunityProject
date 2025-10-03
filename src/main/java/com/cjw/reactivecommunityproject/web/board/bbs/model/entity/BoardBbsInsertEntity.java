package com.cjw.reactivecommunityproject.web.board.bbs.model.entity;

import lombok.Builder;

@Builder
public record BoardBbsInsertEntity(
        String path
        , String name
        , String description
        , String userUid
) {
}
