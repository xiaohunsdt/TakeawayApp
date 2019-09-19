package net.novaborn.takeaway.goods.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.goods.enums.GoodsState;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("goods")
public class Goods extends Model<Goods> {
    private static final long serialVersionUID = -7514580043247682497L;

    @TableId(type = IdType.UUID)
    private String goodsId;

    // 名称
    private String name;

    // 简介
    private String desc;

    // 缩略图
    private String thumb;

    // 月销
    private Integer monthSale;

    // 评分
    private Integer rate;

    //状态
    private GoodsState state;

    private Date createDate;

    private Date updateDate;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;
}
