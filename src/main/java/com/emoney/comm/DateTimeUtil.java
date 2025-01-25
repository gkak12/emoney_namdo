package com.emoney.comm;

import java.time.*;

public class DateTimeUtil {

    private static final String timeZoneName = "Asia/Seoul";

    public static LocalTime getLocalTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalTime();
    }

    public static LocalDate getLocalDate(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDate();
    }

    public static LocalDateTime getLocalDateTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDateTime();
    }
}
