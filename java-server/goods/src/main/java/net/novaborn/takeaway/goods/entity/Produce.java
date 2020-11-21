package net.novaborn.takeaway.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.goods.enums.ProduceState;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * 产品
 *
 * @author xiaohun
 * @since 2020-10-06
 */
@Data
@TableName("`produce`")
public class Produce extends Model<Produce> {
    private static final long serialVersionUID = -7325032748484611059L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称必须设置")
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

    private Long storeId;

    /**
     * 所属类目
     */
    @NotNull(message = "分类必须设置")
    private Long categoryId;

    /**
     * 优先级
     */
    @TableField(value = "`index`")
    private Integer index;

    /**
     * 是否可快递
     */
    private Boolean expressAble;

    /**
     * 评分
     */
    private Integer rate;

    /**
     * 状态
     */
    private ProduceState state;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produce produce = (Produce) o;
        return Objects.equals(id, produce.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
