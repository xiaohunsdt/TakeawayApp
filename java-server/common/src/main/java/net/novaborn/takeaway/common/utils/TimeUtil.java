package net.novaborn.takeaway.common.utils;

import java.util.Date;

public class TimeUtil {
    /**
     * 查看一个时间是否在一个时间段之内
     *
     * @return
     */
    public static boolean isBetween(Date targetDate, Date startTime, Date endTime) {
        int target;
        int start;
        int end;

        target = (int) (targetDate.getTime() % (24 * 60 * 60 * 1000L));
        start = (int) (startTime.getTime() % (24 * 60 * 60 * 1000L));
        end = (int) (endTime.getTime() % (24 * 60 * 60 * 1000L));

        return target > start && target < end;
    }
}
