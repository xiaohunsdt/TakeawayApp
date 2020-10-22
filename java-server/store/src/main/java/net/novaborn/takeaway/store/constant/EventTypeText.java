package net.novaborn.takeaway.store.constant;

import java.util.LinkedHashMap;

public class EventTypeText {
    private static LinkedHashMap<Integer, String> typeText = new LinkedHashMap();

    static {
        typeText.put(1, "[충전] %d원 완료");
        typeText.put(2, "[환전] %d원 신청");
    }

    public static String get(Integer eventType) {
        return typeText.get(eventType);
    }
}
