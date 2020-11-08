package net.novaborn.takeaway.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum WithdrawState {

    /**
     * 等待处理
     */
    WAITING_PROCESS(0),

    /**
     * 完成
     */
    FINISH(1),

    /**
     * 拒绝
     */
    REJECT(2),

    /**
     * 取消
     */
    CANCEL(3);

    WithdrawState(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
