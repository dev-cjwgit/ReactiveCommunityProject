package com.cjw.reactivecommunityproject.web.sys.resourcemgmt.validation;

import jakarta.validation.groups.Default;

public class SysResourceValidationGroup {
    private SysResourceValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
