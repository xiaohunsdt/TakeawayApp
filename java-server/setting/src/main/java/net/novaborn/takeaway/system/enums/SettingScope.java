package net.novaborn.takeaway.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SettingScope {
    /**
     * 等待接单
     */
    STORE("store"),

    EXPRESS("express"),

    SYSTEM("system");

    SettingScope(String scope) {
        this.scope = scope;
    }

    @EnumValue
    private final String scope;
}
