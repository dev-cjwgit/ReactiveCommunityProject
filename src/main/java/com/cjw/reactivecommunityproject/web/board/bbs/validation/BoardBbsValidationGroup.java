package com.cjw.reactivecommunityproject.web.board.bbs.validation;

import jakarta.validation.groups.Default;

public class BoardBbsValidationGroup {
    private BoardBbsValidationGroup() {

    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }
}
