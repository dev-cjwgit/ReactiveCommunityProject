package com.cjw.reactivecommunityproject.common.spring.config.filter;

import com.cjw.reactivecommunityproject.common.security.filter.requestlimit.RequestLimitingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final RequestLimitingFilter requestLimitingFilter;

    @Bean
    public FilterRegistrationBean<RequestLimitingFilter> rateLimitingFilter() {
        FilterRegistrationBean<RequestLimitingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestLimitingFilter);
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setOrder(1); // 필터 순서 설정
        return registrationBean;
    }
}
