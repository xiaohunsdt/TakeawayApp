package net.novaborn.takeaway.common.utils.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum MapExceptionEnum implements ServiceExceptionEnum {

    ADDRESS_ERROR(500, "地址错误!"),
    REQUEST_API_ERROR(500, "请求API数据失败!"),
    REQUEST_ERROR(500, "请求数据失败!"),
    NO_ADDRESS_ERROR(500, "没有这个地址! %s");

    MapExceptionEnum(int code, String message) {
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
