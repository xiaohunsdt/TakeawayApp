package net.novaborn.takeaway.coupon.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum CouponState {

    /**
     * 未使用
     */
    UN_USE(0),

    /**
     * 已使用
     */
    USED( 1),

    /**
     * 已过期
     */
    EXPIRED( 2);

    CouponState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
