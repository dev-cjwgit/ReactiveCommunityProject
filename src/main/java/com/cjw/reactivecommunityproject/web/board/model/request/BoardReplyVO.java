package com.cjw.reactivecommunityproject.web.board.model.request;

import com.cjw.reactivecommunityproject.web.board.validation.BoardValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BoardReplyVO(
        @NotNull(groups = {BoardValidationGroup.Reply.class}, message = "답글의 부모는 null 일 수 없습니다.")
        Long parentUid,

        @NotBlank(groups = {BoardValidationGroup.Reply.class}, message = "제목은 공백일 수 없습니다.")
        @Size(groups = {BoardValidationGroup.Reply.class}
                , min = 1
                , max = 100
                , message = "제목은 1글자 이상 100글자 이하입니다."
        )
        String title,

        @NotBlank(groups = {BoardValidationGroup.Reply.class}, message = "내용은 공백일 수 없습니다.")
        @Size(groups = {BoardValidationGroup.Reply.class}
                , min = 10
                , message = "내용은 최소한 10글자는 이상 작성해야합니다."
        )
        String contents

) {
}
