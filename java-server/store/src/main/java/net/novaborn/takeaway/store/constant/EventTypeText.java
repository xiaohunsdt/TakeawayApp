package net.novaborn.takeaway.store.constant;

import java.util.LinkedHashMap;

public class EventTypeText {
    private static LinkedHashMap<Integer, String> typeText = new LinkedHashMap();

    static {
        typeText.put(1, "[微信支付]订单id: %d(#%d), 收入 %d 韩币!");
        typeText.put(2, "[服务费]订单id: %d(#%d), 订单金额: %d, 收取 %d 韩币!");
        typeText.put(3, "[提现服务]申请提现 %d 韩币, 手续费 %d 韩币!");
        typeText.put(4, "[提现服务]提现退回! 退还 %d 韩币!");
    }

    public static String get(Integer eventType) {
        return typeText.get(eventType);
    }
}
