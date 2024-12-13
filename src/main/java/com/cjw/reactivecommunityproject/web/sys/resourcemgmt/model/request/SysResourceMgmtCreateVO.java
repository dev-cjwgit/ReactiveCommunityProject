package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.validation.SysResourceValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SysResourceMgmtCreateVO(
        @NotNull(groups = {SysResourceValidationGroup.CreateResource.class}, message = "HTTP 메서드는 null 일 수 없습니다.")
        RcManageResourceMethodEnum method,

        @NotBlank(groups = {SysResourceValidationGroup.CreateResource.class}, message = "URL 패턴은 공백일 수 없습니다.")
        @Size(groups = {SysResourceValidationGroup.CreateResource.class},
                min = 1, max = 255,
                message = "1자 이상 500자 이하여야 합니다."
        )
        @Pattern(groups = {SysResourceValidationGroup.CreateResource.class},
                regexp = "^/.*",
                message = "URL 패턴은 '/'로 시작해야 합니다."
        )
        String urlPattern,

        @NotBlank(groups = {SysResourceValidationGroup.CreateResource.class}, message = "설명은 공백 일 수 없습니다.")
        @Size(groups = {SysResourceValidationGroup.CreateResource.class},
                max = 200,
                message = "설명은 200자 이하여야 합니다."
        )
        String description
) {
}
