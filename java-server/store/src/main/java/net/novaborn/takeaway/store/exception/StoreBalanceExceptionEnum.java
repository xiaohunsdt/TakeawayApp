package net.novaborn.takeaway.store.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * order 业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum StoreBalanceExceptionEnum implements ServiceExceptionEnum {
    /**
     * 更新失败
     */
    UPDATE_FAILED(500, "更新失败!"),
    /**
     * 店铺不存在
     */
    NOT_EXIST(500, "不存在!");

    StoreBalanceExceptionEnum(int code, String message) {
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
