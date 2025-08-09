package com.cjw.reactivecommunityproject.common.spring.util;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtils {
    public static String extractClientIp(HttpServletRequest req) {
        var forwarded = req.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            // 첫 번째 값이 원본 IP
            return forwarded.split(",")[0].trim();
        }
        return req.getRemoteAddr();
    }
}
