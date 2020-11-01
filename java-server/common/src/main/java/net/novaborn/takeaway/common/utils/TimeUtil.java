package net.novaborn.takeaway.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
//        LocalTime targetTime;
//        LocalTime startTime;
//        LocalTime endTime;
//
//        if (target == null) {
//            targetTime = LocalTime.now();
//        } else {
//            targetTime = target.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
//        }
//
//        startTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
//        endTime = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
//        return targetTime.isAfter(startTime) && targetTime.isBefore(endTime);
        Calendar tempTarget = Calendar.getInstance(TimeZone.getDefault());
        tempTarget.setTime(target);

        Calendar tempBegin = Calendar.getInstance(TimeZone.getDefault());
        tempBegin.setTime(start);
        tempBegin.set(tempTarget.get(Calendar.YEAR), tempTarget.get(Calendar.MONTH), tempTarget.get(Calendar.DATE));

        Calendar tempEnd = Calendar.getInstance(TimeZone.getDefault());
        tempEnd.setTime(end);
        tempEnd.set(tempTarget.get(Calendar.YEAR), tempTarget.get(Calendar.MONTH), tempTarget.get(Calendar.DATE));

        if (tempEnd.before(tempBegin)) {
            if (tempTarget.before(tempBegin)) {
                tempBegin.set(Calendar.DATE, tempTarget.get(Calendar.DATE) - 1);
            } else {
                tempEnd.set(Calendar.DATE, tempTarget.get(Calendar.DATE) + 1);
            }
        }

        return tempTarget.after(tempBegin) && tempTarget.before(tempEnd);
    }

    public static boolean isBefore(Date start, Date end) {
        Calendar tempBegin = Calendar.getInstance(TimeZone.getDefault());
        tempBegin.setTime(start);

        Calendar tempEnd = Calendar.getInstance(TimeZone.getDefault());
        tempEnd.setTime(end);
        tempEnd.set(tempBegin.get(Calendar.YEAR), tempBegin.get(Calendar.MONTH), tempBegin.get(Calendar.DATE));

        return tempBegin.before(tempEnd);
    }

    public static boolean isAfter(Date start, Date end) {
        Calendar tempBegin = Calendar.getInstance(TimeZone.getDefault());
        tempBegin.setTime(start);

        Calendar tempEnd = Calendar.getInstance(TimeZone.getDefault());
        tempEnd.setTime(end);
        tempEnd.set(tempBegin.get(Calendar.YEAR), tempBegin.get(Calendar.MONTH), tempBegin.get(Calendar.DATE));

        return tempBegin.after(tempEnd);
    }

    public static long between(Date date1, Date date2) {
        Calendar tempBegin = Calendar.getInstance(TimeZone.getDefault());
        tempBegin.setTime(date1);

        Calendar tempEnd = Calendar.getInstance(TimeZone.getDefault());
        tempEnd.setTime(date2);
        tempEnd.set(tempBegin.get(Calendar.YEAR), tempBegin.get(Calendar.MONTH), tempBegin.get(Calendar.DATE));

        if (tempEnd.before(tempBegin)) {
            tempEnd.set(Calendar.DATE, tempBegin.get(Calendar.DATE) + 1);
        }
        return (tempEnd.getTimeInMillis() - tempBegin.getTimeInMillis()) / 1000;
    }

    public static String toString(Date date1) {
        LocalTime time1;
        time1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return StrUtil.format("{}:{}", time1.getHour(), time1.getMinute());
    }
}
