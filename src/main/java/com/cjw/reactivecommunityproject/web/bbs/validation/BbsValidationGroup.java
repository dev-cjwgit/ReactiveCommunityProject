package com.cjw.reactivecommunityproject.web.bbs.validation;

import jakarta.validation.groups.Default;

public class BbsValidationGroup {
    private BbsValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
