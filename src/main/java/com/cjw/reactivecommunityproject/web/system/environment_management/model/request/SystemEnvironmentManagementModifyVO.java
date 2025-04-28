package com.cjw.reactivecommunityproject.web.system.environment_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import com.cjw.reactivecommunityproject.web.system.environment_management.validation.SystemEnvironmentManagementValidationGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SystemEnvironmentManagementModifyVO(
        @NotBlank(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}, message = "환경코드 id는 공백일 수 없습니다.")
        @Size(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , max = 200
                , message = "환경코드 id 는 200자 이하여야 합니다."
        )
        @Pattern(regexp = "^[a-z.]+$"
                , groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , message = "환경코드 id는 영소문자 및 '.'만 포함할 수 있습니다."
        )
        String id,

        @NotNull(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}, message = "환경코드 타입은 null 일 수 없습니다.")
        RcCommonEnvCodeTypeEnum type,

        @NotBlank(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}, message = "환경코드 값은 공백 일 수 없습니다.")
        @Size(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , max = 100
                , message = "값은 100자 이하여야 합니다."
        )
        String value,

        @Nullable
        @Size(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , min = 2, max = 200
                , message = "2자 이상 200자 이하여야 합니다."
        )
        @Pattern(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , regexp = "^[가-힣a-zA-Z0-9-_ ]+$"
                , message = "description 은 한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String description,

        @Size(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}
                , max = 20
                , message = "카테고리는 20자 이하여야 합니다."
        )
        @Pattern(regexp = "^[a-z.]+$"
                , groups = {SystemEnvironmentManagementValidationGroup.Create.class}
                , message = "카테고리는 영소문자 및 '.'만 포함할 수 있습니다."
        )
        String category,

        Integer order,

        @NotNull(groups = {SystemEnvironmentManagementValidationGroup.Modify.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
