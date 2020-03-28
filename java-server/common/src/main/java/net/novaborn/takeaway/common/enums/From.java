package net.novaborn.takeaway.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum From {
    /**
     * 延世学联
     */
    YONSEI(1);

    From(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
