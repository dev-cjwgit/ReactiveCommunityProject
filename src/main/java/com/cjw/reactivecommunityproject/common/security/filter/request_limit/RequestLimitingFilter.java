package com.cjw.reactivecommunityproject.common.security.filter.request_limit;

import com.cjw.reactivecommunityproject.common.spring.util.EnvCodeUtils;
import com.cjw.reactivecommunityproject.server.cache.info.custom.service.CacheInfoCustomService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@Component
@RequiredArgsConstructor
public class RequestLimitingFilter extends OncePerRequestFilter {
    private final CacheInfoCustomService cacheInfoCustomService;
    private Cache<String, TokenBucket> buckets;

    private synchronized void initCacheIfNecessary(int timeWindowSec) {
        if (buckets == null) {
            buckets = Caffeine.newBuilder()
                    .expireAfterAccess(Duration.ofSeconds(timeWindowSec))
                    .build();
        }
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        var limit = EnvCodeUtils.convertEnvCodeByValue(cacheInfoCustomService.getCommonEnvCode("rc.request.filter.limit"), Integer.class);
        var windowSec = EnvCodeUtils.convertEnvCodeByValue(cacheInfoCustomService.getCommonEnvCode("rc.request.filter.time.window.sec"), Integer.class);

        if (limit == null || windowSec == null || limit <= 0 || windowSec <= 0) {
            log.error("requestLimitingFilter.doFilterInternal env code is null");
            chain.doFilter(request, response);
            return;
        }
        this.initCacheIfNecessary(windowSec);

        // 버킷 생성
        var ip = extractClientIp(request);
        var bucket = buckets.get(ip, k -> new TokenBucket(limit, windowSec));

        // 토큰 차감 및 회수 계산
        var now = System.currentTimeMillis();
        if (!bucket.tryConsume(now)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
            log.warn("RateLimit exceeded: ip={}, remainingTokens=0", ip);
            return;
        }

        chain.doFilter(request, response);
    }

    private String extractClientIp(HttpServletRequest req) {
        var forwarded = req.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            // 첫 번째 값이 원본 IP
            return forwarded.split(",")[0].trim();
        }
        return req.getRemoteAddr();
    }

    private static class TokenBucket {
        private final int capacity;
        private final double refillPerMillis;
        private double tokens;
        private long lastRefillTimestamp;

        TokenBucket(int capacity, int windowSec) {
            this.capacity = capacity;
            this.refillPerMillis = (double) capacity / TimeUnit.SECONDS.toMillis(windowSec);
            this.tokens = capacity;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        synchronized boolean tryConsume(long nowMillis) {
            refill(nowMillis);
            if (tokens >= 1d) {
                tokens -= 1d;
                return true;
            }
            return false;
        }

        private void refill(long nowMillis) {
            long elapsed = nowMillis - lastRefillTimestamp;
            if (elapsed > 0) {
                double refill = elapsed * refillPerMillis;
                tokens = Math.min(capacity, tokens + refill);
                lastRefillTimestamp = nowMillis;
            }
        }
    }
}
