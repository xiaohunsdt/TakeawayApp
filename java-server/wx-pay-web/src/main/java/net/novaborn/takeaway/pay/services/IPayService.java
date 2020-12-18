package net.novaborn.takeaway.pay.services;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.RefundLog;

public interface IPayService {
    /**
     * 创建微信支付预支付信息，微信支付所必须的信息字段
     *
     * @param openId
     * @param orderId
     * @param ipAddr  客户端的ip
     * @return
     */
    WxPayMpOrderResult createPayInfo(String openId, Long orderId, String ipAddr);

    /**
     * 确认微信支付的状态
     *
     * @param orderId
     */
    void confirmPay(Long orderId);

    void confirmPay(Long orderId, int totalPrice, String state);

    Tip refundPay(RefundLog refundLog);

    void confirmRefund(Long refundId, int refundPrice, String state);
}
