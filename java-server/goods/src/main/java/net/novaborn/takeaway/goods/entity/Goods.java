package net.novaborn.takeaway.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.novaborn.takeaway.goods.enums.GoodsState;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */

@Data
@TableName("`goods`")
public class Goods extends Model<Goods> {
    private static final long serialVersionUID = -7514580043247682497L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 简介
     */
    @TableField(value = "`desc`")
    private String desc;

    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 标记 热门 新品
     */
    private String flags;

    /**
     * 所属类目
     */
    private String categoryId;

    private Integer price;

    /**
     * 月销
     */
    private Integer monthSale;

    /**
     * 评分
     */
    private Integer rate;

    /**
     * 状态
     */
    private GoodsState state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
