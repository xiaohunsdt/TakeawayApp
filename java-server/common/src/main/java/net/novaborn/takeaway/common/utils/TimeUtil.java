package net.novaborn.takeaway.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    /**
     * 查看一个时间是否在一个时间段之内
     *
     * @return
     */
    public static boolean isBetween(String start, String end) {
        return isBetween(null, start, end);
    }

    public static boolean isBetween(String target, String start, String end) {
        LocalTime targetTime;
        LocalTime startTime;
        LocalTime endTime;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneOffset.UTC);

        if (target == null) {
            targetTime = LocalTime.now();
        } else {
            targetTime = LocalDateTime.parse(target, dateTimeFormatter).toLocalTime();
        }

        startTime = ZonedDateTime.parse(start, dateTimeFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
        endTime = ZonedDateTime.parse(end, dateTimeFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();

        return targetTime.isAfter(startTime) && targetTime.isBefore(endTime);
    }
}
