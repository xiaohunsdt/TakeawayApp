package net.novaborn.takeaway.user.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum AddressExceptionEnum implements ServiceExceptionEnum {

    PHONE_FORMAT_ERROR(500, "手机号格式错误!!!"),
    NO_ADDRESS_ERROR(500, "没有这个地址!!"),
    ADDRESS_NO_COORDINATE_ERROR(500, "错误: 地址没有坐标!!!"),
    STORE_ADDRESS_NO_COORDINATE_ERROR(500, "错误: 店地址没有坐标!!!");

    AddressExceptionEnum(int code, String message) {
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
