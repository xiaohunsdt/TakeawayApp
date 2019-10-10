package net.novaborn.takeaway.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`address`")
public class Address extends Model<Address> {
    private static final long serialVersionUID = -3379009665983595457L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userId;

    /**
     * 具体地址
     */
    private String address;

    /**
     * 地址详细说明
     */
    private String detail;

    private String phone;

    private Boolean isDefault;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}