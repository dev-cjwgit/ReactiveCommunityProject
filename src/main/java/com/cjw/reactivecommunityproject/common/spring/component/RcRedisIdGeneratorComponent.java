package com.cjw.reactivecommunityproject.common.spring.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RcRedisIdGeneratorComponent {
    private static final String LOG_EXCEPTION_KEY = "log_exception_id";
    private static final String LOG_API_KEY = "log_api_id";

    private final RedisTemplate<String, Object> redisTemplate;

    public Long getNextLogApiId(){
        try {
            return redisTemplate.opsForValue().increment(LOG_API_KEY);
        } catch (Exception e) {
            log.error("RcRedisIdGeneratorComponent.getNextLogExceptionId()", e);
            return null;
        }
    }

    public Long getNextLogExceptionId() {
        try {
            return redisTemplate.opsForValue().increment(LOG_EXCEPTION_KEY);
        } catch (Exception e) {
            log.error("RcRedisIdGeneratorComponent.getNextLogExceptionId()", e);
            return null;
        }
    }
}