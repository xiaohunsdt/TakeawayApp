package net.novaborn.takeaway.common.utils;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

public class TimeUtilTest {

    @Test
    public void isBetween() {
        System.out.println(TimeUtil.isBetween(DateUtil.now(),"2019-11-14 11:00:00", "2019-11-15 22:30:00"));
    }
}