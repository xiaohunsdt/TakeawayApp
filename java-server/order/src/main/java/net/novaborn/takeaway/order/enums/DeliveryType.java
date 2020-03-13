package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum DeliveryType {

    /**
     * 尽快配送
     */
    NORMAL(1),

    /**
     * 预约配送
     */
    APPOINTMENT(2);

    DeliveryType(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
