package com.cjw.reactivecommunityproject.common.spring.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DateUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private DateUtils(){
    }

    public static ZonedDateTime convert(String inputDate, String format) {
        if (StringUtils.isBlank(inputDate) || StringUtils.isBlank(format)) {
            log.debug("DateUtils.convert is null : {} - {}", inputDate, format);
            return null;
        }

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
