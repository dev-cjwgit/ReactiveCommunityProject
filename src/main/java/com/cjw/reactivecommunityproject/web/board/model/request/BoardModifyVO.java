package com.cjw.reactivecommunityproject.web.board.model.request;

import com.cjw.reactivecommunityproject.web.board.validation.BoardValidationGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

public record BoardModifyVO(
        @Nullable
        @Size(groups = {BoardValidationGroup.Modify.class}
                , min = 1
                , max = 100
                , message = "제목은 1글자 이상 100글자 이하입니다."
        )
        String title,

        @Nullable
        @Size(groups = {BoardValidationGroup.Modify.class}
                , min = 10
                , max = 65535
                , message = "제목은 100글자를 넘을 수 없습니다."
        )
        String contents
) {
}
