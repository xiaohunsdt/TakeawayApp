package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`order_item`")
public class OrderItem extends Model<OrderItem> {
    private static final long serialVersionUID = -6354741085522616794L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long orderId;

    private Long produceId;

    private Long goodsId;

    @NotBlank
    private String goodsName;

    private String goodsThumb;

    @NotNull
    @Min(value = 1)
    private Integer goodsPrice;

    @NotNull
    @Min(value = 1)
    private Integer goodsCount;
}
