package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderState {
    /**
     * 等待接单
     */
    WAITING_RECEIVE(0),

    /**
     * 生产中
     */
    PRODUCING(1),

    /**
     * 配送中
     */
    DELIVERING(2),

    /**
     * 等待评论
     */
    WAIT_COMMENT(3),

    /**
     * 已完成
     */
    FINISHED(4),

    /**
     * 退款
     */
    REFUND(5),

    /**
     * 退款
     */
    EXPIRED(6);

    OrderState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
