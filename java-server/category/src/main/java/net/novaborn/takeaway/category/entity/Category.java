package net.novaborn.takeaway.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`category`")
public class Category extends Model<Category> {
    private static final long serialVersionUID = 2694607880571063999L;

    @TableId(type = IdType.UUID)
    private String id;

    // 名称
    private String name;

    private String parentId;

    private Date createDate;

    private Date updateDate;

    @TableLogic
    private Integer deleted;
}
