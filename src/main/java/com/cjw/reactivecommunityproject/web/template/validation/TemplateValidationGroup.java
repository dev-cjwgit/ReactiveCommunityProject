package com.cjw.reactivecommunityproject.web.template.validation;

import jakarta.validation.groups.Default;

public class TemplateValidationGroup {
    private TemplateValidationGroup() {

    }

    public interface Template extends Default {
    }
}
