package com.cjw.reactivecommunityproject.common.security.filter.requestlimit;

import com.cjw.reactivecommunityproject.common.security.model.RequestUserInfo;
import com.cjw.reactivecommunityproject.common.spring.util.EnvCodeUtils;
import com.cjw.reactivecommunityproject.server.cache.custom.service.CacheCustomService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLimitingFilter extends OncePerRequestFilter {
    private final CacheCustomService cacheCustomService;
    private final ConcurrentHashMap<String, RequestUserInfo> requestCounts = new ConcurrentHashMap<>();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        var limitEnvCode = EnvCodeUtils.<Integer>convertEnvCodeByValue(cacheCustomService.getCustomCommonEnvCode("rc.request.filter.limit"), Integer.class);
        var timeWindowEnvCode = EnvCodeUtils.<Integer>convertEnvCodeByValue(cacheCustomService.getCustomCommonEnvCode("rc.request.filter.time.window.sec"), Integer.class);

        if (limitEnvCode == null || timeWindowEnvCode == null) {
            log.error("RequestLimitingFilter.doFilterInternal env code is null");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        log.debug("RequestLimitingFilter.doFilterInternal: {}, {}", clientIP, currentTime);
        requestCounts.compute(clientIP, (ip, requestUserInfo) -> {
            if (requestUserInfo == null || (currentTime - requestUserInfo.getStartTime() > timeWindowEnvCode * 1000L)) {
                // 새로운 윈도우 시작
                return RequestUserInfo.builder()
                        .startTime(currentTime)
                        .requestCount(new AtomicInteger(1))
                        .build();
            } else {
                // 기존 윈도우 업데이트
                requestUserInfo.incrementRequestCount();
                return requestUserInfo;
            }
        });

        var userRequestInfo = requestCounts.get(clientIP);
        log.debug("RequestLimitingFilter.doFilterInternal: request info : {}", userRequestInfo);
        if (userRequestInfo != null && userRequestInfo.getRequestCount().get() > limitEnvCode) {
            // 요청 제한 초과
            response.setStatus(429); // 429 Too Many Requests
            response.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
