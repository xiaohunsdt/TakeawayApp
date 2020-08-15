package net.novaborn.takeaway.common.exception;


/**
 * 所有业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum SysExceptionEnum implements ServiceExceptionEnum {

    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(400, "账号密码错误"),
    ARGUMENT_VALID_ERROR(400, "参数验证错误!"),
    AUTH_HAVE_NO_USER(400, "找不到此用户名!"),
    PERMISSION_DENIED(500,"没有权限"),
    UPLOAD_IMAGE_FAILED(400, "上传图片失败!"),
    WRITE_ERROR(500, "渲染界面错误");

    SysExceptionEnum(int code, String message) {
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
