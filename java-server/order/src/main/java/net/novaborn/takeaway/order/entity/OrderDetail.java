package net.novaborn.takeaway.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.common.enums.From;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("`order_detail`")
public class OrderDetail extends Model<OrderDetail> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 预约时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentDate;

    private Boolean isCommented;

    /**
     * 备注消息
     */
    private String ps;

    /**
     * 來自哪里
     */
    @TableField(value = "`from`")
    private From from;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;
}
