package com.cjw.reactivecommunityproject.common.spring.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "rc")
public record RcProperties(
        ConfigProperties config
        , CacheManagerProperties cacheManager
) {
    public record ConfigProperties(
            String defaultRegion
            , String defaultLanguage
    ) {
    }

    public record CacheManagerProperties(
            int expiresMinutes
    ) {
    }
}