package net.novaborn.takeaway.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品规格
 *
 * @author xiaohun
 * @since 2020-10-06
 */
@Data
@TableName("`specification`")
public class Specification extends Model<Specification> {
    private static final long serialVersionUID = -718407715403904234L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "`key`")
    private String key;

    private Long storeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
}
