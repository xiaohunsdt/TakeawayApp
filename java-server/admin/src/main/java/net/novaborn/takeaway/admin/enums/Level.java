package net.novaborn.takeaway.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum Level {
    /**
     * 超级管理员
     */
    SUPER_MANAGER(0),

    /**
     * 公司管理员
     */
    BUSINESS_MANAGER(1),

    /**
     * 店管理员
     */
    SHOP_MANAGER(2),

    /**
     * 接单员
     */
    RECEIVER(3),

    /**
     * 外卖员
     */
    DELIVERER(4);

    Level(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
