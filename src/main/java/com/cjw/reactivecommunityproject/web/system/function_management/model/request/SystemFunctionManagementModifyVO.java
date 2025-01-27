package com.cjw.reactivecommunityproject.web.system.function_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageFunctionTypeEnum;
import com.cjw.reactivecommunityproject.web.system.function_management.validation.SystemFunctionManagementValidationGroup;
import com.cjw.reactivecommunityproject.web.system.role_management.validation.SystemRoleManagementValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SystemFunctionManagementModifyVO(
        @NotNull(groups = {SystemFunctionManagementValidationGroup.Modify.class}, message = "uid 는 null 일 수 없습니다.")
        Long uid,

        @NotBlank(groups = {SystemFunctionManagementValidationGroup.Modify.class}, message = "name 은 공백일 수 없습니다.")
        @Size(groups = {SystemFunctionManagementValidationGroup.Modify.class},
                min = 2, max = 100,
                message = "2자 이상 100자 이하여야 합니다."
        )
        @Pattern(groups = {SystemFunctionManagementValidationGroup.Modify.class},
                regexp = "^[가-힣a-zA-Z0-9-_ ]+$",
                message = "한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String name,

        @NotNull(groups = {SystemFunctionManagementValidationGroup.Modify.class}, message = "type 는 null 일 수 없습니다.")
        RcManageFunctionTypeEnum type,

        @NotBlank(groups = {SystemFunctionManagementValidationGroup.Modify.class}, message = "description 은 공백일 수 없습니다.")
        @Size(groups = {SystemFunctionManagementValidationGroup.Modify.class},
                min = 2, max = 200,
                message = "2자 이상 200자 이하여야 합니다."
        )
        @Pattern(groups = {SystemFunctionManagementValidationGroup.Modify.class},
                regexp = "^[가-힣a-zA-Z0-9-_ ]+$",
                message = "한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String description,

        @NotNull(groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
