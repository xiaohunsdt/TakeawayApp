package net.novaborn.takeaway.system.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum SettingExceptionEnum implements ServiceExceptionEnum {


    SCOPE_NOT_EXIST(500, "域不存在!"),
    UPDATE_ERROR(500, "更新失败!");

    SettingExceptionEnum(int code, String message) {
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
