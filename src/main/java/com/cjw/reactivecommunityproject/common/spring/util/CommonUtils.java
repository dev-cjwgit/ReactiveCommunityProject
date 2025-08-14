package com.cjw.reactivecommunityproject.common.spring.util;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

    @Nullable
    public static String extractClientIp(@NotNull HttpServletRequest req) {
        try {
            var forwarded = req.getHeader("X-Forwarded-For");
            if (forwarded != null && !forwarded.isBlank()) {
                // 첫 번째 값이 원본 IP
                return forwarded.split(",")[0].trim();
            }
            return req.getRemoteAddr();
        } catch (Exception e) {
            log.error("CommonUtils.extractClientIp Exception:", e);
            return null;
        }
    }
}
