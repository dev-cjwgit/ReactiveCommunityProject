package com.cjw.reactivecommunityproject.web.system.role_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.web.system.role_management.validation.SystemRoleManagementValidationGroup;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SystemRoleManagementModifyVO(

        @NotNull(groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "uid 는 null 일 수 없습니다.")
        @Min(value = 1, groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "uid 은 1 이상이어야 합니다.")
        @Max(value = 127, groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "uid 은 128 미만이어야 합니다.")
        Integer uid,

        @NotBlank(groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "name 은 공백일 수 없습니다.")
        @Size(groups = {SystemRoleManagementValidationGroup.Modify.class},
                min = 3, max = 100,
                message = "3자 이상 500자 이하여야 합니다."
        )
        @Pattern(groups = {SystemRoleManagementValidationGroup.Modify.class},
                regexp = "^[가-힣a-zA-Z0-9-_ ]+$",
                message = "한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String name,

        @NotNull(groups = {SystemRoleManagementValidationGroup.Modify.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
