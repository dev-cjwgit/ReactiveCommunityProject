package com.cjw.reactivecommunityproject.web.system.role_management.model.request;

import com.cjw.reactivecommunityproject.web.system.role_management.validation.SystemRoleManagementValidationGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SystemRoleManagementCreateVO(
        @Min(value = 1, groups = {SystemRoleManagementValidationGroup.Create.class}, message = "uid 은 1 이상이어야 합니다.")
        @Max(value = 127, groups = {SystemRoleManagementValidationGroup.Create.class}, message = "uid 은 128 미만이어야 합니다.")
        Integer uid,
        @NotBlank(groups = {SystemRoleManagementValidationGroup.Create.class}, message = "name 은 공백일 수 없습니다.")
        @Size(groups = {SystemRoleManagementValidationGroup.Create.class}
                , min = 3, max = 100
                , message = "3자 이상 100자 이하여야 합니다."
        )
        @Pattern(groups = {SystemRoleManagementValidationGroup.Create.class}
                , regexp = "^[가-힣a-zA-Z0-9-_ ]+$"
                , message = "한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String name,


        @Nullable
        @Size(groups = {SystemRoleManagementValidationGroup.Create.class}
                , min = 2, max = 200
                , message = "2자 이상 200자 이하여야 합니다."
        )
        @Pattern(groups = {SystemRoleManagementValidationGroup.Create.class}
                , regexp = "^[가-힣a-zA-Z0-9-_ ]+$"
                , message = "description 은 한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String description
) {
}
