package net.novaborn.takeaway.common.utils;

import cn.hutool.core.util.ReUtil;

public class PhoneUtil {
    private static String VALIDATE_REG = "^((10)|(010))\\d{8}$";

    public static boolean validate(String phone) {
        return ReUtil.contains(VALIDATE_REG, phone);
    }
}
