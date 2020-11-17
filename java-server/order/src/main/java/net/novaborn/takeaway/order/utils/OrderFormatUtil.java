package net.novaborn.takeaway.order.utils;

import net.novaborn.takeaway.order.enums.*;

import java.util.HashMap;
import java.util.Map;

import static net.novaborn.takeaway.order.enums.OrderState.*;
import static net.novaborn.takeaway.order.enums.OrderType.*;
import static net.novaborn.takeaway.order.enums.PayState.*;
import static net.novaborn.takeaway.order.enums.PaymentWay.*;

public class OrderFormatUtil {
    private static Map<OrderState, String> orderStateMap = new HashMap<>();

    private static Map<OrderType, String> orderTypeMap = new HashMap<>();

    private static Map<PayState, String> payStateMap = new HashMap<>();

    private static Map<PaymentWay, String> paymentWayMap = new HashMap<>();

    static {
        orderStateMap.put(WAITING_RECEIVE, "等待接单");
        orderStateMap.put(PRODUCING, "制作中");
        orderStateMap.put(DELIVERING, "配送中");
        orderStateMap.put(FINISHED, "已完成");
        orderStateMap.put(REFUND, "退款");
        orderStateMap.put(EXPIRED, "过期");

        orderTypeMap.put(NORMAL, "一般订单");
        orderTypeMap.put(APPOINTMENT, "预约订单");
        orderTypeMap.put(IN_STORE, "堂食订单");
        orderTypeMap.put(EXPRESS, "快递订单");
        orderTypeMap.put(SELF, "自取订单");

        payStateMap.put(UN_PAY, "未支付");
        payStateMap.put(PAID, "已支付");
        payStateMap.put(PAY_LATER, "后付");

        paymentWayMap.put(BALANCE, "账户余额");
        paymentWayMap.put(TRANSFER, "通帐转帐");
        paymentWayMap.put(WEIXIN_PAY, "微信支付");
        paymentWayMap.put(ALI_PAY, "支付宝支付");
        paymentWayMap.put(CREDIT_CARD, "刷卡支付");
        paymentWayMap.put(CASH, "现金");
    }

    public static String formatOrderState(OrderState state) {
        return orderStateMap.get(state);
    }

    public static String formatPayState(PayState state) {
        return payStateMap.get(state);
    }

    public static String formatPaymentWay(PaymentWay paymentWay) {
        return paymentWayMap.get(paymentWay);
    }

    public static String formatOrderType(OrderType orderType) {
        return orderTypeMap.get(orderType);
    }
}
