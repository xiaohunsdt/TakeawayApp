package net.novaborn.takeaway.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SettingScope {
    STORE("store"),

    EXPRESS("express"),

    SYSTEM("system"),

    PAYMENT("payment"),

    PRINTER("printer");

    SettingScope(String scope) {
        this.scope = scope;
    }

    @EnumValue
    private final String scope;
}
