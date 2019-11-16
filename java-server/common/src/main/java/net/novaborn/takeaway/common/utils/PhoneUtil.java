package net.novaborn.takeaway.common.utils;

import cn.hutool.core.util.ReUtil;

/**
 * 验证韩国手机号是否正确
 */
public class PhoneUtil {
    private static String VALIDATE_REG = "^((10)|(010))\\d{8}$";

    public static boolean validate(String phone) {
        return ReUtil.contains(VALIDATE_REG, phone);
    }
}
