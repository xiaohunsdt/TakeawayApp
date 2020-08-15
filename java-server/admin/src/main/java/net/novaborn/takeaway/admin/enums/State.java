package net.novaborn.takeaway.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum State {
    /**
     * 冻结
     */
    STOP(0),

    /**
     * 正常
     */
    NORMAL(1);

    State(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
