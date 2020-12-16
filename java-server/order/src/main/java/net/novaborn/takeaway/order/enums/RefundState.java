package net.novaborn.takeaway.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum RefundState {
    /**
     * 处理中
     */
    PROCESSING(0),

    /**
     * 完成
     */
    DONE(1),

    /**
     * 失败
     */
    FAILED(2);

    @EnumValue
    private final int code;

    RefundState(int code) {
        this.code = code;
    }
}
