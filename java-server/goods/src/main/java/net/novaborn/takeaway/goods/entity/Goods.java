package net.novaborn.takeaway.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.enums.ProduceState;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`goods`")
@NoArgsConstructor
public class Goods extends Model<Goods> {
    private static final long serialVersionUID = -7514580043247682497L;

    @Id
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long produceId;

    private String title;

    private Integer price;

    /**
     * 坐标
     */
    private String indexes;

    /**
     * 特有属性
     */
    private String ownSpecs;

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

    public Goods(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
