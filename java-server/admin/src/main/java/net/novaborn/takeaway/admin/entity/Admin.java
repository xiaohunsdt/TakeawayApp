package net.novaborn.takeaway.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`admin`")
public class Admin extends Model<Admin> {
    private static final long serialVersionUID = 4163511315665773300L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userName;

    private String password;

    private Date loginDate;

    private Date createDate;

    //    @TableField(value = "update_time", update = "now()")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;
}
