package com.cjw.reactivecommunityproject.web.auth.validation;

import jakarta.validation.groups.Default;

public class AuthValidationGroup {
    private AuthValidationGroup() {

    }

    public interface register extends Default {
    }

    public interface login extends Default {
    }

}
