package net.novaborn.takeaway.common.utils;

import org.junit.Test;

public class CommonUtilTest {

    @Test
    public void validate() {
        System.out.println(CommonUtil.validatePhone("01056511996"));
        System.out.println(CommonUtil.validatePhone("1056511996"));
    }
}