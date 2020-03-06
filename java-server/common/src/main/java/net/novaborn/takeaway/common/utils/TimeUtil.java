package net.novaborn.takeaway.common.utils;

import cn.hutool.core.date.DateUtil;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {
    /**
     * 查看一个时间是否在一个时间段之内
     *
     * @return
     */
    public static boolean isBetween(String start, String end) {
        return isBetween(new Date(), DateUtil.parse(start, "yyyy-MM-dd HH:mm:ss"), DateUtil.parse(end, "yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean isBetween(String target, String start, String end) {
        return isBetween(target, start, end, "yyyy-MM-dd HH:mm:ss");
    }

    public static boolean isBetween(Date target, String start, String end) {
        return isBetween(target, DateUtil.parse(start, "yyyy-MM-dd HH:mm:ss"), DateUtil.parse(end, "yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean isBetween(String target, String start, String end, String formatter) {
        return isBetween(DateUtil.parse(target, formatter), DateUtil.parse(start, formatter), DateUtil.parse(end, formatter));
    }

    public static boolean isBetween(Date target, Date start, Date end) {
        LocalTime targetTime;
        LocalTime startTime;
        LocalTime endTime;

        if (target == null) {
            targetTime = LocalTime.now();
        } else {
            targetTime = target.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }

        startTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        endTime = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return targetTime.isAfter(startTime) && targetTime.isBefore(endTime);
    }

    public static LocalTime parseLocalTime(String target, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(formatter)
                .withZone(ZoneId.systemDefault());
        return ZonedDateTime.parse(target, dateTimeFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
    }
}
