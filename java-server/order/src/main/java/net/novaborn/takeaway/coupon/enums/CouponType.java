package net.novaborn.takeaway.coupon.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum CouponType {

    /**
     * 现金卷
     */
    MONEY(1),

    /**
     * 折扣卷
     */
    DISCOUNT( 2);

    CouponType(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
