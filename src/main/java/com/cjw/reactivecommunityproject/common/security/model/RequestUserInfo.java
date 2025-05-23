package com.cjw.reactivecommunityproject.common.security.model;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class RequestUserInfo {
    private final long startTime;
    private final AtomicInteger requestCount;

    public void incrementRequestCount() {
        this.requestCount.incrementAndGet();
    }
}
