package com.cjw.reactivecommunityproject.web.board.model.request;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record BoardListVO(
        String bbs
        , String title
        , String contents
        , int hit
        , String hitOperator
        , ZonedDateTime startDate
        , ZonedDateTime endDate
) {
}
