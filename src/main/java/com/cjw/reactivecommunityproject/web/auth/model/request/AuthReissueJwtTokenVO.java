package com.cjw.reactivecommunityproject.web.auth.model.request;

import com.cjw.reactivecommunityproject.web.auth.validation.AuthValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthReissueJwtTokenVO(
        @NotBlank(groups = {AuthValidationGroup.ReissueRefreshToken.class}, message = "Refresh token은 공백일 수 없습니다.")
        @Pattern(groups = {AuthValidationGroup.ReissueRefreshToken.class}
                , regexp = "^[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+\\.[A-Za-z0-9_-]+$"
                , message = "유효하지 않은 JWT 형식입니다."
        )
        String refreshToken
) {
}
