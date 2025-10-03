package com.cjw.reactivecommunityproject.web.board.post.model.request;

import com.cjw.reactivecommunityproject.web.board.post.validation.BoardPostValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardPostCreateVO(
        @NotBlank(groups = {BoardPostValidationGroup.Create.class}, message = "제목은 공백일 수 없습니다.")
        @Size(groups = {BoardPostValidationGroup.Create.class}
                , min = 1
                , max = 100
                , message = "제목은 1글자 이상 100글자 이하입니다."
        )
        String title,

        @NotBlank(groups = {BoardPostValidationGroup.Create.class}, message = "내용은 공백일 수 없습니다.")
        @Size(groups = {BoardPostValidationGroup.Create.class}
                , min = 10
                , message = "내용은 최소한 10글자는 이상 작성해야합니다."
        )
        String contents

) {
}
