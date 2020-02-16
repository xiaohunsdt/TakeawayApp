package net.novaborn.takeaway.category.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`category`")
public class Category extends Model<Category> {
    private static final long serialVersionUID = 2694607880571063999L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    // 名称
    private String name;

    // 父 id
    private String parentId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
