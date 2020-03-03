package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 归纳后的订单状态
 */
@Getter
public enum OrderStateEx {
    /**
     * 等待支付
     */
    WAIT_PAY(0),

    /**
     * 等待就餐
     */
    WAIT_EAT(1),

    /**
     * 等待评价
     */
    WAIT_COMMENT(2),

    /**
     * 退款
     */
    REFUND(3);

    OrderStateEx(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
