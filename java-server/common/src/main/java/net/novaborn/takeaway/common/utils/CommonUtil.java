package net.novaborn.takeaway.common.utils;

import cn.hutool.core.util.ReUtil;

public class CommonUtil {
    private static String VALIDATE_REG = "^((10)|(010))\\d{8}$";

    /**
     * 验证韩国手机号是否正确
     */
    public static boolean validatePhone(String phone) {
        return ReUtil.contains(VALIDATE_REG, phone);
    }

    /**
     * 验证韩国地址是否正确
     */
    public static boolean validateAddress(String address) {
        return true;
    }
}
