package com.cjw.reactivecommunityproject.web.auth.validation;

import jakarta.validation.groups.Default;

public class AuthValidationGroup {
    private AuthValidationGroup() {

    }

    public interface Register extends Default {
    }

    public interface Login extends Default {
    }

    public interface ReissueRefreshToken extends Default {

    }

}
