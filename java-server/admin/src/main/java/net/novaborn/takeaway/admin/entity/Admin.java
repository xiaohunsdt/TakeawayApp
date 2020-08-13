package net.novaborn.takeaway.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.admin.enums.Level;
import net.novaborn.takeaway.admin.enums.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`admin`")
public class Admin extends Model<Admin> {
    private static final long serialVersionUID = 4163511315665773300L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @NotBlank(message = "用户名必须设置")
    private String userName;

    @NotBlank(message = "密码必须设置")
    private String password;

    /**
     * 父账号
     */
    private String parentIds;

    @NotNull(message = "级别必须设置")
    @TableField(value = "`level`")
    private Level level;

    @TableField(value = "`state`")
    private State state;

    private Date loginDate;

    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;
}
