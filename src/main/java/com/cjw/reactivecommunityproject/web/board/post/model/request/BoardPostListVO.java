package com.cjw.reactivecommunityproject.web.board.post.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record BoardPostListVO(
        String bbs
        , String title
        , String contents
        , int hit
        , String hitOperator
        , ZonedDateTime startDate
        , ZonedDateTime endDate
) {
}
