package net.novaborn.takeaway.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum State {
    /**
     * 停止
     */
    OFF(0),

    /**
     * 正常
     */
    ON(1);

    State(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
