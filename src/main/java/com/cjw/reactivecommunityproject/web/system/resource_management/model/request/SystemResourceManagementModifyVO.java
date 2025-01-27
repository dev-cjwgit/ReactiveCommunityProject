package com.cjw.reactivecommunityproject.web.system.resource_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.web.system.resource_management.validation.SystemResourceManagementValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SystemResourceManagementModifyVO(
        @NotNull(groups = {SystemResourceManagementValidationGroup.Modify.class}, message = "uid 는 null 일 수 없습니다.")
        Long uid,

        @NotNull(groups = {SystemResourceManagementValidationGroup.Modify.class}, message = "HTTP 메서드는 null 일 수 없습니다.")
        RcManageResourceMethodEnum method,


        @NotBlank(groups = {SystemResourceManagementValidationGroup.Modify.class}, message = "urlPattern 은 값은 null 일 수 없습니다.")
        @Size(groups = {SystemResourceManagementValidationGroup.Modify.class},
                max = 255,
                message = "최대 사이즈는 255자 이하여야 합니다."
        )
        @Pattern(groups = {SystemResourceManagementValidationGroup.Modify.class},
                regexp = "^/([a-z-*]+(/)?)*$",
                message = "URL 패턴은 '/'로 시작해야 하며, 경로는 영어 소문자, 하이픈(-), '*', '/'만 사용할 수 있습니다."
        )
        String urlPattern,

        @NotBlank(groups = {SystemResourceManagementValidationGroup.Modify.class}, message = "description 은 값은 null 일 수 없습니다.")
        @Size(groups = {SystemResourceManagementValidationGroup.Modify.class},
                max = 200,
                message = "설명은 200자 이하여야 합니다."
        )
        String description,

        @NotNull(groups = {SystemResourceManagementValidationGroup.Modify.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
