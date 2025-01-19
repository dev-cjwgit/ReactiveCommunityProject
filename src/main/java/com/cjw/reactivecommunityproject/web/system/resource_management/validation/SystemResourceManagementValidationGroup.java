package com.cjw.reactivecommunityproject.web.system.resource_management.validation;

import jakarta.validation.groups.Default;

public class SystemResourceManagementValidationGroup {
    private SystemResourceManagementValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
