package com.cjw.reactivecommunityproject.common.spring.pagination.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaginationUtils {
    private PaginationUtils() {
    }

    public static Map<String, Object> convertFieldsToMap(Object object) {
        Map<String, Object> result = new HashMap<>();
        if (object == null) {
            return result;
        }
        var clazz = object.getClass();
        var fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(o -> {
            try {
                o.setAccessible(true);
                result.put(o.getName(), o.get(object));
            } catch (IllegalAccessException e) {
                log.error("PaginationUtils.convertFieldsToMap: {}", e.getMessage());
            }
        });
        return result;
    }
}
