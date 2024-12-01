package com.cjw.reactivecommunityproject.common.spring.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@ConfigurationProperties(prefix = "rc")
public record RcProperties(
        ConfigProperties config,
        JwtProperties jwt,
        CacheProperties cache
) implements Serializable {
    public record ConfigProperties(
            String defaultRegion
    ) implements Serializable {
    }

    public record JwtProperties(
            long accessTokenExpiresMinutes,
            long refreshTokenExpiresMinutes,
            String secretKey
    ) implements Serializable {
    }

    public record CacheProperties(
            int expiresMinutes
    ) implements Serializable {
    }
}