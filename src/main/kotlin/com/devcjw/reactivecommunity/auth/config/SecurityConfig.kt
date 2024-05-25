package com.devcjw.reactivecommunity.auth.config

import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers


@Slf4j
@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    val PUBLIC_ACCESS_PATH = arrayOf(
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/swagger-resources/**",
        "/webjars/**"
    )

    @Bean
    fun securityFilter(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .logout(ServerHttpSecurity.LogoutSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

            .securityMatcher(NegatedServerWebExchangeMatcher(ServerWebExchangeMatchers.pathMatchers(*PUBLIC_ACCESS_PATH)))

        return http.build();
    }
}