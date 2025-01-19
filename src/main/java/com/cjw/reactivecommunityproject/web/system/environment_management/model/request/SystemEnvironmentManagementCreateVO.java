package com.cjw.reactivecommunityproject.web.system.environment_management.model.request;

import com.cjw.reactivecommunityproject.common.spring.model.entity.RcCommonEnvCodeTypeEnum;
import com.cjw.reactivecommunityproject.web.system.environment_management.validation.SystemEnvironmentManagementValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SystemEnvironmentManagementCreateVO(
        @NotBlank(groups = {SystemEnvironmentManagementValidationGroup.Create.class}, message = "환경코드 id는 공백일 수 없습니다.")
        String id,

        @NotNull(groups = {SystemEnvironmentManagementValidationGroup.Create.class}, message = "환경코드 타입은 null 일 수 없습니다.")
        RcCommonEnvCodeTypeEnum type,

        @NotBlank(groups = {SystemEnvironmentManagementValidationGroup.Create.class}, message = "환경코드 값은 공백 일 수 없습니다.")
        @Size(groups = {SystemEnvironmentManagementValidationGroup.Create.class},
                max = 100,
                message = "값은 100자 이하여야 합니다."
        )
        String value,

        @Size(groups = {SystemEnvironmentManagementValidationGroup.Create.class},
                max = 20,
                message = "카테고리는 20자 이하여야 합니다."
        )
        String category,

        Integer order
) {
}
