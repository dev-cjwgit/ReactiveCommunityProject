package com.cjw.reactivecommunityproject.web.board.validation;

import jakarta.validation.groups.Default;

public class BoardValidationGroup {
    private BoardValidationGroup() {
    }

    public interface Create extends Default {
    }

    public interface Modify extends Default {
    }

    public interface Recommend extends Default {
    }
}
