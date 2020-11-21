package net.novaborn.takeaway.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.common.enums.From;
import net.novaborn.takeaway.order.enums.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`order`")
public class Order extends Model<Order> {
    private static final long serialVersionUID = -6354741085522616794L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 当天的编号
     */
    private Integer number;

    @NotNull(message = "店铺不能为空")
    private Long storeId;

    private Long userId;

    private Long addressId;

    private Integer goodsCount;

    /**
     * 折扣
     */
    private Short discount;

    /**
     * 优惠的价格
     */
    private Integer discountedPrices;

    /**
     * 原价
     */
    private Integer allPrice;

    /**
     * 真实价格 = 原价 - 优惠的价格
     */
    private Integer realPrice;

    /*
     *  配送费
     */
    private Integer deliveryPrice;

    @NotNull(message = "支付方式不能为空")
    private PaymentWay paymentWay;

    private OrderType orderType;

    private PayState payState;

    private OrderState orderState;

//    /**
//     * 备注消息
//     */
//    private String ps;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date appointmentDate;
//
//    private Boolean isCommented;
//
//    /**
//     * 來自哪里
//     */
//    @TableField(value = "`from`")
//    private From from;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
