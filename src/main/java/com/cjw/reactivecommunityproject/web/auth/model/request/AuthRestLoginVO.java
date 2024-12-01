package com.cjw.reactivecommunityproject.web.auth.model.request;

import com.cjw.reactivecommunityproject.web.auth.validation.AuthValidationGroup;
import jakarta.validation.constraints.*;

import java.io.Serializable;

public record AuthRestLoginVO(
        @NotBlank(groups = {AuthValidationGroup.login.class}, message = "이메일은 공백일 수 없습니다.")
        @Email(groups = {AuthValidationGroup.login.class}, message = "이메일 형태가 아닙니다.")
        @Size(groups = {AuthValidationGroup.login.class},
                min = 5, max = 100,
                message = "이메일의 길이는 5글자 이상 100자리 이하여야 합니다."
        )
        String email,

        @NotBlank(groups = {AuthValidationGroup.login.class}, message = "비밀번호는 공백일 수 없습니다.")
        @Size(groups = {AuthValidationGroup.login.class},
                min = 9, max = 20,
                message = "비밀번호는 9글자 이상 20글자 이하여야 합니다."
        )
        @Pattern(groups = {AuthValidationGroup.login.class},
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]*$",
                message = "비밀번호는 공백을 포함할 수 없으며 특수문자, 알파벳, 숫자 조합이 필요합니다."
        )
        String pw
) implements Serializable {
}
