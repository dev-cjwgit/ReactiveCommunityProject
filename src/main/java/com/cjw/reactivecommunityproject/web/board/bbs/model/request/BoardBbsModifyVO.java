package com.cjw.reactivecommunityproject.web.board.bbs.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.web.board.bbs.validation.BoardBbsValidationGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BoardBbsModifyVO(
        @NotNull(groups = {BoardBbsValidationGroup.Modify.class}, message = "uid 는 null 일 수 없습니다.")
        @Min(value = 1, groups = {BoardBbsValidationGroup.Modify.class}, message = "uid 은 1 이상이어야 합니다.")
        @Max(value = Long.MAX_VALUE, groups = {BoardBbsValidationGroup.Modify.class}, message = "uid 은 {value} 이하여야 합니다.")
        Long uid,


        @NotBlank(groups = {BoardBbsValidationGroup.Modify.class}, message = "Bbs의 Path는 공백일 수 없습니다.")
        @Pattern(groups = {BoardBbsValidationGroup.Modify.class}
                , regexp = "^[a-z_]+$"
                , message = "Bbs의 Path는 소문자와 언더스코어(_)만 포함할 수 있습니다."
        )
        @Size(groups = {BoardBbsValidationGroup.Modify.class}
                , min = 1, max = 10
                , message = "Bbs의 Path는 1글자 이상 10자리 이하여야 합니다."
        )
        String path,

        @NotBlank(groups = {BoardBbsValidationGroup.Modify.class}, message = "Name은 공백일 수 없습니다.")
        @Size(groups = {BoardBbsValidationGroup.Modify.class}
                , min = 1, max = 10
                , message = "Bbs의 Name은 1글자 이상 10자리 이하여야 합니다."
        )
        @Pattern(groups = {BoardBbsValidationGroup.Modify.class}
                , regexp = "^[\\p{L}\\p{N}\\s]+$"
                , message = "Name에 특수문자는 입력할 수 없습니다. 공백, 문자, 숫자만 입력 가능합니다."
        )
        String name,

        @Nullable
        @Size(groups = {BoardBbsValidationGroup.Modify.class}
                , min = 2, max = 200
                , message = "2자 이상 200자 이하여야 합니다."
        )
        @Pattern(groups = {BoardBbsValidationGroup.Modify.class}
                , regexp = "^[가-힣a-zA-Z0-9\\-_,. ]+$"
                , message = "description 은 한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( ) 콤마(,) 마침표(.) 만 가능합니다."
        )
        String description,

        @NotNull(groups = {BoardBbsValidationGroup.Modify.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
