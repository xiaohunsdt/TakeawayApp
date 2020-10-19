package net.novaborn.takeaway.goods.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 商品业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum GoodsExceptionEnum implements ServiceExceptionEnum {

    /**
     * 不存在
     */
    GOODS_NOT_FOUND(500, "商品不存在!"),

    /**
     * 更新商品失败
     */
    UPDATE_FAILED(499, "更新商品失败!"),

    /**
     * 商品缺货
     */
    GOODS_IS_SHORTAGE(500, "商品缺货!"),

    /**
     * 商品不可用
     */
    GOODS_IS_OFF(500, "商品不可用!");

    GoodsExceptionEnum(int code, String message) {
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
