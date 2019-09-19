package net.novaborn.takeaway.admin.common.exception;


/**
 * 所有业务异常的枚举
 *
 * @author fengshuonan
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
    WRITE_ERROR(500, "渲染界面错误"),

    /**
     * sbobet
     */
    SBOBET_NO_USER(-100, "Have no sbobet user or password!"),
    SBOBET_COOKIES_INVALID(-100, "sbobet cookies has invalid!"),
    GET_SBOBET_GAME_DATA_ERROR(-101,"get sbobet game failed. please try again!"),
    NET_FOUND_CHROME_DRIVER(-102,"Not found Chrome driver"),
    LOGIN_SBOBET_FAILED(-103,"login sbobet failed! Please check your account!"),

    /**
     * LOGIN_MAX_FAILED
     */
    LOGIN_MAX_FAILED(-120, "login max failed!"),

    /*
     *  Fantasy Error
     */
    FANTASY_USER_PASS_ERROR(-121,"Fantasy username or password error!"),
    FANTASY_LOGIN_FAILED(-121,"login fantasy failed!"),
    FANTASY_GET_GAME_DATA_ERROR(-121,"get fantasy games error!"),
    FANTASY_COOKIES_INVALID(-121,"fantasy cookies has invalid!"),
    FANTASY_NULL_COOKIES(-121,"have no fantasy logined cookies!"),
    FANTASY_NO_USER(-121,"Have no fantasy user or password!"),
    FANTASY_NO_SERVER_URL(-121,"Have no fantasy server url!"),
    FANTASY_VERIFY_FAILED(-122,"fantasy verify failed!"),

    /*
     *  Stage Error
     */
    STAGE_USER_PASS_ERROR(-121,"Stage username or password error!"),
    STAGE_LOGIN_FAILED(-121,"login stage failed!"),
    STAGE_GET_GAME_DATA_ERROR(-121,"get stage games error!"),
    STAGE_COOKIES_INVALID(-121,"Stage cookies has invalid!"),
    STAGE_NULL_COOKIES(-121,"have no stage logined cookies!"),
    STAGE_NO_USER(-121,"Have no stage user or password!"),
    STAGE_NO_SERVER_URL(-121,"Have no stage server url!"),
    STAGE_VERIFY_FAILED(-122,"stage verify failed!"),

    UPDATE_GAME_FAILED(-130,"update game failed!");

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
