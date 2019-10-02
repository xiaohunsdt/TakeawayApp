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
@TableName("`order_item`")
public class OrderItem extends Model<OrderItem> {
    private static final long serialVersionUID = -6354741085522616794L;

    @TableId(type = IdType.UUID)
    private String id;

    private String goodsId;
}
