package net.novaborn.takeaway.coupon.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum CouponTemplateExceptionEnum implements ServiceExceptionEnum {

    HAVE_NO_THIS_TEMPLATE(500, "没有这个模板!!!"),
    HAVE_NO_MONEY_OR_DISCOUNT(500, "面值或折扣必填!!!");

    CouponTemplateExceptionEnum(int code, String message) {
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
