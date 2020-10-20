package net.novaborn.takeaway.goods.exception;


import net.novaborn.takeaway.common.exception.ServiceExceptionEnum;

/**
 * 商品业务异常的枚举
 *
 * @author xiaohun
 * @date 2016年11月12日 下午5:04:51
 */
public enum GoodsStockExceptionEnum implements ServiceExceptionEnum {
    /**
     * 不存在
     */
    STOCK_NOT_FOUND(500, "库存不存在!"),

    /**
     * 不存在
     */
    STOCK_HAD_NONE(500, "已经没有库存了!"),

    /**
     * 更新商品库存失败
     */
    UPDATE_FAILED(499, "更新商品库存失败!");

    GoodsStockExceptionEnum(int code, String message) {
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
