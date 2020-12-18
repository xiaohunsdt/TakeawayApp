package net.novaborn.takeaway.pay.enums;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

public enum PayExceptionEnum implements ServiceExceptionEnum {
    /**
     * order 异常
     */
    PAY_ERROR(500, "支付失败! 状态码: %s"),
    REFUND_ERROR(600, "退款失败! 状态码: %s"),

    /**
     * 没有订单商品
     */
    PAY_CREATE_ERROR(501, "创建支付信息失败!"),
    QUERY_PAY_ERROR(502, "查询支付信息失败!"),
    PAY_PRICE_ERROR(503, "订单支付金额不符合!"),
    PAY_PAID_ERROR(504, "订单已经是已支付状态!"),

    REFUND_LOG_NOT_EXIST(601, "退款记录不存在!"),
    REFUND_PRICE_ERROR(602, "退款记录不存在!"),
    REFUND_HAD_DONE_ERROR(603, "退款记录是已完成状态!");

    PayExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
