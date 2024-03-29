package net.novaborn.takeaway.goods.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ProduceState {
    /**
     * 部分缺货
     */
    PART_SHORTAGE(-2),

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

    ProduceState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
