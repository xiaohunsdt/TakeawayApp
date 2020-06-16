package net.novaborn.takeaway.common.utils;

import net.novaborn.takeaway.common.enums.From;

import java.util.HashMap;
import java.util.Map;

public class FromFormatUtil {
    private static Map<From, String> fromMap = new HashMap<>();

    static {
        fromMap.put(From.YONSEI, "延世大学联");
        fromMap.put(From.EWHA, "梨花大学联");
        fromMap.put(From.HONGIK, "弘益大学联");
        fromMap.put(From.SOGANG, "西江大学联");
    }

    public static String formatOrderState(From from) {
        return fromMap.get(from);
    }
}
