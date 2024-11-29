package com.cjw.reactivecommunityproject.common.security.config;

import com.cjw.reactivecommunityproject.common.security.dao.SecurityDAO;
import com.cjw.reactivecommunityproject.common.security.filter.JwtAuthenticationFilter;
import com.cjw.reactivecommunityproject.common.security.filter.JwtAuthorizationFilter;
import com.cjw.reactivecommunityproject.common.security.handler.JwtAuthenticationFailedHandler;
import com.cjw.reactivecommunityproject.common.security.handler.JwtAuthorizationDeniedHandler;
import com.cjw.reactivecommunityproject.common.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private final SecurityDAO securityDAO;

    private final CorsConfigurationSource corsConfigurationSource;

    private static final String[] ALLOW_PATH_URL_LIST = {"/rest/auth/**"};

    private static final String[] PUBLIC_PATH_LIST = {"/webjars/**", "/opendoc.html", "/rest/auth/**"};

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers(PUBLIC_PATH_LIST);
    }

    @Bean
    public SecurityFilterChain restFilterChain(HttpSecurity http) throws Exception {
        return http
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)

                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .securityMatcher("/rest/**")
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(ALLOW_PATH_URL_LIST)
                                .permitAll()
                                .anyRequest()
                                .access(new JwtAuthorizationFilter(securityDAO))
                )

                .exceptionHandling(exceptionHandle ->
                        exceptionHandle
                                .authenticationEntryPoint(new JwtAuthenticationFailedHandler())
                                .accessDeniedHandler(new JwtAuthorizationDeniedHandler())
                )
                .build();
    }
}
