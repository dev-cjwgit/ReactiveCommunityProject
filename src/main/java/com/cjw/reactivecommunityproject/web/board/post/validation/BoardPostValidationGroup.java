package com.cjw.reactivecommunityproject.web.board.post.validation;

import jakarta.validation.groups.Default;

public class BoardPostValidationGroup {
    private BoardPostValidationGroup() {
    }

    public interface Create extends Default {
    }

    public interface Reply extends Default {
    }

    public interface Modify extends Default {
    }

    public interface Recommend extends Default {
    }
}
