package com.cjw.reactivecommunityproject.web.board.model.request;

import com.cjw.reactivecommunityproject.web.board.validation.BoardValidationGroup;
import jakarta.validation.constraints.NotNull;

public record BoardRecommendVO(
        @NotNull(groups = {BoardValidationGroup.Recommend.class}, message = "게시판 번호는 반드시 존재해야 합니다.")
        Long boardUid
) {
}
