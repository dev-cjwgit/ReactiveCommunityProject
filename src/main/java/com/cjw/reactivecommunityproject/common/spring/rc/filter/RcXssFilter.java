package com.cjw.reactivecommunityproject.common.spring.rc.filter;

import com.cjw.reactivecommunityproject.common.security.xss.wrapper.SecurityXssBodyRequestWrapper;
import com.cjw.reactivecommunityproject.common.security.xss.wrapper.SecurityXssRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.apache.commons.lang3.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

public class RcXssFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain) throws IOException, ServletException {

        HttpServletRequest wrapped = new SecurityXssRequestWrapper(req);
        if (this.isJson(req)) {
            wrapped = new SecurityXssBodyRequestWrapper(wrapped);
        }
        chain.doFilter(wrapped, res);
    }

    private boolean isJson(HttpServletRequest req) {
        String ct = req.getContentType();
        return Strings.CI.equals(ct, "application/json");
    }
}