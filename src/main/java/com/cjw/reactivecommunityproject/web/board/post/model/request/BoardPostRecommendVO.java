package com.cjw.reactivecommunityproject.web.board.post.model.request;

import com.cjw.reactivecommunityproject.web.board.post.validation.BoardPostValidationGroup;
import jakarta.validation.constraints.NotNull;

public record BoardPostRecommendVO(
        @NotNull(groups = {BoardPostValidationGroup.Recommend.class}, message = "게시판 번호는 반드시 존재해야 합니다.")
        Long boardUid
) {
}
