package com.cjw.reactivecommunityproject.web.auth.model.request;

import jakarta.validation.constraints.*;

public record AuthLoginVO(
        @NotBlank
        @Email
        @Max(value = 100)
        String email,

        @NotBlank
        @Pattern(
                regexp = "^(?=(.*[a-z]))(?=(.*[A-Z]))(?=(.*\\d))(?=(.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]))(?!.*\\s).{7,20}$",
                message = "Password must contain at least three of the following: lowercase letter, uppercase letter, number, special character"
        )  // 특수문자, 소문자, 대문자, 숫자 중 최소 3개 이상의 조합
        String pw
) {
}
