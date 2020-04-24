package net.novaborn.takeaway.coupon.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum CouponExceptionEnum implements ServiceExceptionEnum {

    UNSUPPORT_PAYMENT_WAY(5001, "不支持此支付方式"),
    HAVE_NO_COUPON(5002, "没有这个优惠卷"),
    HAD_BOUND(5002, "此优惠卷已经被绑定！"),
    COUPON_CAN_NOT_BE_USED(5003, "此优惠卷不可用"),
    DO_NOT_MEET_MINI_AMOUNT_REQUIREMENTS(5004, "订单总金额不满足此优惠卷最低使用要求"),
    UNKNOWN_ERROR(500, "未知错误!!!");

    CouponExceptionEnum(int code, String message) {
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
