package net.novaborn.takeaway.common.utils;

import org.junit.Test;

public class PhoneUtilTest {

    @Test
    public void validate() {
        System.out.println(PhoneUtil.validate("01056511996"));
        System.out.println(PhoneUtil.validate("1056511996"));
    }
}