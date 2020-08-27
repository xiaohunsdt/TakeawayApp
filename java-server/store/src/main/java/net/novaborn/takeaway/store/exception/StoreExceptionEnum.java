package net.novaborn.takeaway.store.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * order 业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum StoreExceptionEnum implements ServiceExceptionEnum {
    /**
     * 店铺不存在
     */
    STORE_NOT_EXIST(500, "店铺不存在!");

    StoreExceptionEnum(int code, String message) {
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
