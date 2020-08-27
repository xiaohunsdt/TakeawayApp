package net.novaborn.takeaway.store.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PaymentWay {

    /**
     * 免费
     */
    FREE(0),
    /**
     * 按量计费
     */
    PAY_AS_YOU_GO(1),
    /**
     * 包月
     */
    MONTH(2);
   ;

    PaymentWay(int code) {
        this.code = code;
    }

    @EnumValue
    private final int code;
}
