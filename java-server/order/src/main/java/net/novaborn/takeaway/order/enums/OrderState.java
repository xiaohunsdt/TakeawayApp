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
     * 生成中
     */
    PRODUCING(2),

    /**
     * 配送中
     */
    DELIVERING(3),

    /**
     * 已完成
     */
    FINISHED(4);

    OrderState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
