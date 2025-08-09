package com.cjw.reactivecommunityproject.common.spring.config.filter;

import com.cjw.reactivecommunityproject.common.spring.rc.filter.api.logging.RcApiLoggingFilter;
import com.cjw.reactivecommunityproject.common.spring.rc.filter.request.limit.RcRequestLimitingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final RcRequestLimitingFilter rcRequestLimitingFilter;
    private final RcApiLoggingFilter rcApiLoggingFilter;

    @Bean
    public FilterRegistrationBean<RcRequestLimitingFilter> rateLimitingFilter() {
        FilterRegistrationBean<RcRequestLimitingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rcRequestLimitingFilter);
        registrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 필터 순서 설정
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RcApiLoggingFilter> requestApiLoggingFilter() {
        FilterRegistrationBean<RcApiLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(rcApiLoggingFilter);
        registrationBean.addUrlPatterns("/rest/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1); // requestLimitingFilter 이후 실행
        return registrationBean;
    }
}
