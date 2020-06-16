package net.novaborn.takeaway.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品库存
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`goods_stock`")
public class GoodsStock extends Model<GoodsStock> {
    private static final long serialVersionUID = -7514580043247682497L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String goodsId;

    private Integer stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;
}
