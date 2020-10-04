package net.novaborn.takeaway.activity.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum ActivityExceptionEnum implements ServiceExceptionEnum {
    NOT_FOUND(500, "当前活动不存在!!!");

    ActivityExceptionEnum(int code, String message) {
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
