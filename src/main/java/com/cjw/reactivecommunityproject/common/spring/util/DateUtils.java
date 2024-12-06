package com.cjw.reactivecommunityproject.common.spring.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateUtils {
    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    public static ZonedDateTime convert(String inputDate, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate localStartDate = LocalDate.parse(inputDate, formatter);
            return localStartDate.atStartOfDay(ZoneId.of("UTC"));
        } catch (Exception e) {
            log.error("DateUtils.convert: {}", e.getMessage(), e);
        }
        return null;
    }
}
