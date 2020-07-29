package net.novaborn.takeaway.common.exception;

/**
 * 封装guns的异常
 *
 * @author xiaohun
 * @Date 2017/12/28 下午10:32
 */
public class SysException extends RuntimeException {

    private Integer code;

    private String message;

    public SysException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public SysException(ServiceExceptionEnum serviceExceptionEnum, Object... objects) {
        this.code = serviceExceptionEnum.getCode();
        this.message = String.format(serviceExceptionEnum.getMessage(), objects);
    }

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
