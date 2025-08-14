package com.cjw.reactivecommunityproject.common.security.xss.utils;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class SecurityXssSanitizer {
    private static final PolicyFactory POLICY = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public static String clean(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return POLICY.sanitize(input);
    }
}
