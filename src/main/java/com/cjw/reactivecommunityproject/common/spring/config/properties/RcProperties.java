package com.cjw.reactivecommunityproject.common.spring.config.properties;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;


@ConfigurationProperties(prefix = "rc")
public record RcProperties(
        ConfigProperties config,
        CacheManageProperties cacheManage,
        SchedulerProperties scheduler,
        HttpProperties http
) {
    public record ConfigProperties(
            String defaultLanguage,
            String defaultRegion
    ) {}

    public record CacheManageProperties(
            int expiresMinutes
    ) {}

    public record SchedulerProperties(
            BatchProperties batchCacheReload,
            BatchProperties batchCacheOk
    ) {
        public record BatchProperties(
                String cron,
                boolean enabled
        ) {}
    }

    public record HttpProperties(
            Timeouts timeouts,
            Pool pool,
            Codecs codecs
    ) {
        public record Timeouts(
                Duration connect,
                Duration response,
                Duration read,
                Duration write,
                Duration request
        ) {}

        public record Pool(
                int maxConnections,
                Duration maxIdleTime,
                int pendingAcquireMaxCount
        ) {}

        public record Codecs(
                DataSize maxInMemorySize
        ) {}
    }
}