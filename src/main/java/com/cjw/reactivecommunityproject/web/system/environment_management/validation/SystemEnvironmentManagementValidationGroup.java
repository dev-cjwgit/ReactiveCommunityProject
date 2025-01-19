package com.cjw.reactivecommunityproject.web.system.environment_management.validation;

import jakarta.validation.groups.Default;

public class SystemEnvironmentManagementValidationGroup {
    private SystemEnvironmentManagementValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
