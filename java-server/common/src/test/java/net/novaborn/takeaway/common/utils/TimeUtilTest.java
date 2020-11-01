package net.novaborn.takeaway.common.utils;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

public class TimeUtilTest {

    @Test
    public void isBetween() {
        System.out.println(TimeUtil.isBetween("2019-11-30 03:10:00", "2019-11-14 22:00:00", "2019-11-15 03:30:00"));
    }

    @Test
    public void between() {
        System.out.println(TimeUtil.between(DateUtil.parseDateTime("2019-11-30 01:59:00"), DateUtil.parseDateTime("2019-11-14 02:00:00")));
    }
}