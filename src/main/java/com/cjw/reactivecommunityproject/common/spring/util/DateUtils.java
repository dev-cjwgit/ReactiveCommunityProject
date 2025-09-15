package com.cjw.reactivecommunityproject.common.spring.util;

import jakarta.annotation.Nullable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DateUtils {
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    @Nullable
    public static String convertZonedDateTimeToString(@NonNull ZonedDateTime inDateTime, @NonNull String outFormat) {
        return convertZonedDateTimeToString(inDateTime, outFormat, null);
    }

    @Nullable
    public static String convertZonedDateTimeToString(@NonNull ZonedDateTime inDateTime, @NonNull String outFormat, @Nullable String timezone) {
        try {
            if (StringUtils.isBlank(outFormat))
                return null;

            ZoneId zone = (StringUtils.isBlank(timezone))
                    ? inDateTime.getZone()
                    : ZoneId.of(timezone.trim());

            DateTimeFormatter f = DateTimeFormatter.ofPattern(outFormat).withZone(zone);

            return inDateTime.format(f);
        } catch (ZoneRulesException zoneRulesException) {
            log.warn("Invalid Timezone");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Nullable
    public static ZonedDateTime convertStringDateToZonedDateTime(@Nullable String inDate, @NonNull String inFormat) {
        if (StringUtils.isBlank(inDate) || StringUtils.isBlank(inFormat)) {
            log.debug("DateUtils.convert is null : {} - {}", inDate, inFormat);
            return null;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inFormat);
            LocalDate localStartDate = LocalDate.parse(inDate, formatter);
            return localStartDate.atStartOfDay(ZoneId.of("UTC"));
        } catch (Exception e) {
            log.error("DateUtils.convert: {}", e.getMessage(), e);
            return null;
        }
    }
}
