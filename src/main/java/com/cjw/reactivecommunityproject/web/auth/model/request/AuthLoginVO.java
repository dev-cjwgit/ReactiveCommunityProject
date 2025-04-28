package com.cjw.reactivecommunityproject.web.auth.model.request;

import com.cjw.reactivecommunityproject.web.auth.validation.AuthValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AuthLoginVO(
        @NotBlank(groups = {AuthValidationGroup.Login.class}, message = "이메일은 공백일 수 없습니다.")
        @Email(groups = {AuthValidationGroup.Login.class}, message = "이메일 형태가 아닙니다.")
        @Size(groups = {AuthValidationGroup.Login.class}
                , min = 5, max = 100
                , message = "이메일의 길이는 5글자 이상 100자리 이하여야 합니다."
        )
        String email,

        @NotBlank(groups = {AuthValidationGroup.Login.class}, message = "비밀번호는 공백일 수 없습니다.")
        @Size(groups = {AuthValidationGroup.Login.class}
                , min = 9, max = 20
                , message = "비밀번호는 9글자 이상 20글자 이하여야 합니다."
        )
        @Pattern(groups = {AuthValidationGroup.Login.class}
                , regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]*$"
                , message = "비밀번호는 공백을 포함할 수 없으며 특수문자, 알파벳, 숫자 조합이 필요합니다."
        )
        String pw,

        @NotNull(groups = {AuthValidationGroup.Login.class}, message = "중복 로그인 여부는 null 일 수 없습니다.")
        Boolean duplicationLogin
) {
}
