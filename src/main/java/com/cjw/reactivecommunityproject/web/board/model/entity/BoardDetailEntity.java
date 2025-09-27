package com.cjw.reactivecommunityproject.web.board.model.entity;

import java.time.ZonedDateTime;

public record BoardDetailEntity(
        long uid
        , long parentUid
        , long bbsUid
        , String title
        , int hit
        , String createdUserUid
        , ZonedDateTime createdUtcAt
        , String updatedUserUid
        , ZonedDateTime updatedUtcAt
) {
}
