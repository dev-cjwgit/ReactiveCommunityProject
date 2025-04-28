package com.cjw.reactivecommunityproject.web.system.resource_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.web.system.resource_management.validation.SystemResourceManagementValidationGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SystemResourceManagementCreateVO(
        @NotNull(groups = {SystemResourceManagementValidationGroup.Create.class}, message = "HTTP 메서드는 null 일 수 없습니다.")
        RcManageResourceMethodEnum method,

        @NotBlank(groups = {SystemResourceManagementValidationGroup.Create.class}, message = "URL 패턴은 공백일 수 없습니다.")
        @Size(groups = {SystemResourceManagementValidationGroup.Create.class},
                min = 1, max = 500,
                message = "1자 이상 500자 이하여야 합니다."
        )
        @Pattern(groups = {SystemResourceManagementValidationGroup.Create.class},
                regexp = "^/([a-z-*]+(/)?)*$",
                message = "URL 패턴은 '/'로 시작해야 하며, 경로는 영어 소문자, 하이픈(-), '*', '/'만 사용할 수 있습니다."
        )
        String urlPattern,

        @Nullable
        @Size(groups = {SystemResourceManagementValidationGroup.Create.class},
                min = 2, max = 200,
                message = "2자 이상 200자 이하여야 합니다."
        )
        @Pattern(groups = {SystemResourceManagementValidationGroup.Create.class},
                regexp = "^[가-힣a-zA-Z0-9-_ ]+$",
                message = "description 은 한글 및 영문(대/소) 숫자 그리고 하이픈(-) 언더바(_) 공백( )만 가능합니다."
        )
        String description
) {
}
