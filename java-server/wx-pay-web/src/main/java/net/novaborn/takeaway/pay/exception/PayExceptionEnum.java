package net.novaborn.takeaway.pay.exception;

import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

public enum PayExceptionEnum implements ServiceExceptionEnum {
    /**
     * order 异常
     */
    PAY_ERROR(500, "支付失败!"),

    /**
     * 没有订单商品
     */
    PAY_CREATE_ERROR(500, "创建支付信息失败!");

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
