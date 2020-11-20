package net.novaborn.takeaway.goods.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 商品业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum ProduceExceptionEnum implements ServiceExceptionEnum {

    /**
     * 商品不可用
     */
    PRODUCE_EXPRESS_UNABLE(500, "%s, 此商品暂时不支持快递服务!");

    ProduceExceptionEnum(int code, String message) {
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
