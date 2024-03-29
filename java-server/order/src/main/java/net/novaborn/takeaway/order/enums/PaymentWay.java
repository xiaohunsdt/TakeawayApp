package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PaymentWay {

    /**
     * 账户余额
     */
    BALANCE(0),

    /**
     * 通帐转帐
     */
    TRANSFER(1),

    /**
     * 微信支付
     */
    WEIXIN_PAY(2),

    /**
     * 支付宝支付
     */
    ALI_PAY(3),

    /**
     * 刷卡支付
     */
    CREDIT_CARD( 4),

    /**
     * 现金
     */
    CASH( 5);

    PaymentWay(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
