package net.novaborn.takeaway.user.entity;

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
@TableName("`user`")
public class User extends Model<User> {
    private static final long serialVersionUID = 24295584861601121L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userName;

    private String password;

    private String nickName;

    private String openId;

    private String avatar;

    private Short gender;

    private Integer level;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    private Integer version;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}