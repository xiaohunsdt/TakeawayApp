package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PaymentWay {

    /**
     * 微信支付
     */
    WEIXIN_PAY(0),

    /**
     * 支付宝支付
     */
    ALI_PAY(0),

    /**
     * 已支付
     */
    CREDIT_CARD( 1),

    /**
     * 现金
     */
    CASH( 1);

    PaymentWay(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
