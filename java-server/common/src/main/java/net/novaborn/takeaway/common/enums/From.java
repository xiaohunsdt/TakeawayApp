package net.novaborn.takeaway.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum From {
    /**
     * 延世学联
     */
    YONSEI(1),

    /**
     * 弘益学联
     */
    HONGIK(2),

    /**
     * 梨花学联
     */
    EWHA(3),

    /**
     * 西江学联
     */
    SOGANG(4);

    From(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
