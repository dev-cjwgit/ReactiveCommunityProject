package com.devcjw.reactivecommunity.auth.config

import com.devcjw.reactivecommunity.auth.config.handler.RcJwtFilter
import com.devcjw.reactivecommunity.auth.service.JwtService
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
class SecurityConfig(
    private val jwtService: JwtService,
    private val rcServerAuthenticationEntryPoint: ServerAuthenticationEntryPoint,
    private val rcServerAccessDeniedHandler: ServerAccessDeniedHandler,
    private val rcReactiveAuthorizationManager: ReactiveAuthorizationManager<AuthorizationContext>,
) {

    val PUBLIC_ACCESS_PATH = arrayOf(
        "/auth/**",
        "/webjars/**",
        "/swagger-ui.html/**"
    )

    @Bean
    fun securityFilter(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .logout(ServerHttpSecurity.LogoutSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

            // 하위 경로는 Filter 무시
            .securityMatcher(NegatedServerWebExchangeMatcher(ServerWebExchangeMatchers.pathMatchers(*PUBLIC_ACCESS_PATH)))

            .authorizeExchange { authorize ->
                authorize
                    .pathMatchers(*PUBLIC_ACCESS_PATH).permitAll()
                    .anyExchange().access(rcReactiveAuthorizationManager)
            }

            .addFilterBefore(RcJwtFilter(jwtService), SecurityWebFiltersOrder.AUTHENTICATION)

            .exceptionHandling {
                it.authenticationEntryPoint(rcServerAuthenticationEntryPoint)
                it.accessDeniedHandler(rcServerAccessDeniedHandler)
            }
        return http.build()
    }
}