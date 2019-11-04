package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PayState {

    /**
     * 未支付
     */
    UN_PAY(0),

    /**
     * 已支付
     */
    PAID( 1),

    /**
     * 后付
     */
    PAY_LATER( 2);

    PayState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
