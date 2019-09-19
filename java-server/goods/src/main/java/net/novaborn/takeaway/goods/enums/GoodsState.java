package net.novaborn.takeaway.goods.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GoodsState {
    /**
     * 缺货
     */
    SHORTAGE(-1),

    /**
     * 下架
     */
    OFF(0),

    /**
     * 上架
     */
    ON(1);

    GoodsState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
