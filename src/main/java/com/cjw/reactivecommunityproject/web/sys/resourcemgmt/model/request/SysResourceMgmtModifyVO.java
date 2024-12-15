package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.CommonEnabledEnum;
import com.cjw.reactivecommunityproject.common.spring.model.entity.RcManageResourceMethodEnum;
import com.cjw.reactivecommunityproject.web.sys.resourcemgmt.validation.SysResourceValidationGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SysResourceMgmtModifyVO(
        @NotNull(groups = {SysResourceValidationGroup.Modify.class}, message = "필수 값은 null 일 수 없습니다.")
        Long uid,

        RcManageResourceMethodEnum method,

        @Size(groups = {SysResourceValidationGroup.Modify.class},
                max = 255,
                message = "최대 사이즈는 500자 이하여야 합니다."
        )
        @Pattern(groups = {SysResourceValidationGroup.Modify.class},
                regexp = "^/.*",
                message = "URL 패턴은 '/'로 시작해야 합니다."
        )
        String urlPattern,

        @Size(groups = {SysResourceValidationGroup.Create.class},
                max = 200,
                message = "설명은 200자 이하여야 합니다."
        )
        String description,

        @NotNull(groups = {SysResourceValidationGroup.Create.class}, message = "enabled 는 null 일 수 없습니다.")
        CommonEnabledEnum enabled
) {
}
