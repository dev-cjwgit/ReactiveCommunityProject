package com.cjw.reactivecommunityproject.common.spring.config.filter;

import com.cjw.reactivecommunityproject.common.spring.rc.filter.api_logging.ApiLoggingFilter;
import com.cjw.reactivecommunityproject.common.spring.rc.filter.request_limit.RequestLimitingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final RequestLimitingFilter requestLimitingFilter;
    private final ApiLoggingFilter apiLoggingFilter;

    @Bean
    public FilterRegistrationBean<RequestLimitingFilter> rateLimitingFilter() {
        FilterRegistrationBean<RequestLimitingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestLimitingFilter);
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 필터 순서 설정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ApiLoggingFilter> requestApiLoggingFilter() {
        FilterRegistrationBean<ApiLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(apiLoggingFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1); // requestLimitingFilter 이후 실행
        return registrationBean;
    }
}
