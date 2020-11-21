package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderType {
    /**
     * 一般
     */
    NORMAL(1),

    /**
     * 预约
     */
    APPOINTMENT(2),

    /**
     * 堂食
     */
    IN_STORE(3),

    /**
     * 快递
     */
    EXPRESS(4),

    /**
     * 自提
     */
    SELF(5);

    OrderType(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
