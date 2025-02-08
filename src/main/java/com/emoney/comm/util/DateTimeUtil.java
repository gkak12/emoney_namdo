package com.emoney.comm.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String timeZoneName = "Asia/Seoul";

    public static LocalTime getLocalTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalTime();
    }

    public static String convertLocalTimeToString(String pattern, LocalTime localTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localTime);
    }

    public static LocalDate getLocalDate(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDate();
    }

    public static String convertLocalDateToString(String pattern, LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDate);
    }

    public static LocalDateTime getLocalDateTime(){
        return ZonedDateTime
                .now(ZoneId.of(timeZoneName))
                .toLocalDateTime();
    }

    public static String convertLocalDateTimeToString(String pattern, LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime);
    }
}
