package com.cjw.reactivecommunityproject.web.system.function_management.validation;

import jakarta.validation.groups.Default;

public class SystemFunctionManagementValidationGroup {
    private SystemFunctionManagementValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
