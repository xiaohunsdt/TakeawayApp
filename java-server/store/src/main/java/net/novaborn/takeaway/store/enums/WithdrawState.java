package net.novaborn.takeaway.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum WithdrawState {

    /**
     * 免费
     */
    WAITING_PROCESS(0),
    /**
     * 按量计费
     */
    FINISH(1),
    /**
     * 包月
     */
    REJECT(2);

    WithdrawState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
