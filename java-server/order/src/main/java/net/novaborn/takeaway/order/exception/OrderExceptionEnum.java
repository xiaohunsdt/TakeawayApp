package net.novaborn.takeaway.order.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum OrderExceptionEnum implements ServiceExceptionEnum {

    /**
     * order 异常
     */
    ORDER_NOT_EXIST(500, "订单不存在!"),

    /**
     * 没有订单商品
     */
    ORDER_NOT_ITEMS(500, "没有订单商品!"),
    ORDER_HAVE_FINISHED(500, "订单已经完成!"),
    ORDER_NOT_FINISHED(500, "订单还没有完成!"),
    ORDER_STATE_ERROR(500, "当前的状态无法支持此操作!"),
    ORDER_CAN_NOT_CREATE_FOR_NOW(500,"当前时间段无法下单!"),
    ORDER_BELOW_LOWEST_DELIVERY_PRICE(500, "订单金额低于起送价格!");

    OrderExceptionEnum(int code, String message) {
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
