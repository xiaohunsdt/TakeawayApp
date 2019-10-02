package net.novaborn.takeaway.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.enums.PaymentWay;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`order`")
public class Order extends Model<Order> {
    private static final long serialVersionUID = -6354741085522616794L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userId;

    private Integer allPrice;

    private Integer goodsCount;

    private PaymentWay paymentWay;

    private PayState payState;

    private OrderState orderState;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    private Integer version;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
