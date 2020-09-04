package net.novaborn.takeaway.pay.services;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;

public interface IPayService {
    /**
     * 创建微信支付预支付信息，微信支付所必须的信息字段
     * @param openId
     * @param orderId
     * @param ipAddr 客户端的ip
     * @return
     */
    WxPayMpOrderResult createPayInfo(String openId, Long orderId, String ipAddr);

    /**
     * 确认微信支付的状态
     * @param orderId
     */
    void confirmPay(Long orderId);
}
