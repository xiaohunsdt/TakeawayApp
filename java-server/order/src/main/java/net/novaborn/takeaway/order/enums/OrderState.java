package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderState {
    /**
     * 等待支付
     */
    WAITING_PAY(0),

    /**
     * 生产中
     */
    PRODUCING(1),

    /**
     * 配送中
     */
    DELIVERING(2),

    /**
     * 等待付款
     */
    WAIT_COMMENT(3),

    /**
     * 已完成
     */
    FINISHED(4),

    /**
     * 退款
     */
    REFUND(5);

    OrderState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
