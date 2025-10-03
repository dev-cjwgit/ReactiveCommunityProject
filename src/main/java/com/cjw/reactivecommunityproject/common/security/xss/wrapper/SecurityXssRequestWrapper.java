package com.cjw.reactivecommunityproject.common.security.xss.wrapper;

import com.cjw.reactivecommunityproject.common.security.xss.utils.SecurityXssSanitizer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.LinkedHashMap;
import java.util.Map;

public class SecurityXssRequestWrapper extends HttpServletRequestWrapper {
    public SecurityXssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return SecurityXssSanitizer.clean(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null)
            return new String[0];
        String[] cleaned = new String[values.length];
        for (int i = 0; i < values.length; i++) cleaned[i] = SecurityXssSanitizer.clean(values[i]);
        return cleaned;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> src = super.getParameterMap();
        Map<String, String[]> dst = LinkedHashMap.newLinkedHashMap(src.size());
        src.forEach((k, v) -> {
            String[] arr = new String[v.length];
            for (int i = 0; i < v.length; i++) arr[i] = SecurityXssSanitizer.clean(v[i]);
            dst.put(k, arr);
        });
        return dst;
    }

    @Override
    public String getHeader(String name) {
        return SecurityXssSanitizer.clean(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return SecurityXssSanitizer.clean(super.getQueryString());
    }
}