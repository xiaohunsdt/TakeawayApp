package net.novaborn.takeaway.common.utils;

import org.junit.Test;

public class TimeUtilTest {

    @Test
    public void isBetween() {
        System.out.println(TimeUtil.isBetween("2019-11-14T15:00:01.000Z", "2019-11-15T13:30:00.000Z"));
    }
}