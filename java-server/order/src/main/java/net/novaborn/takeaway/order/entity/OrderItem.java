package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

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

    private String orderId;

    private String goodsId;

    private String goodsName;

    private String goodsThumb;

    private Integer goodsPrice;

    private Integer goodsCount;
}
