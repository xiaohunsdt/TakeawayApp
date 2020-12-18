package net.novaborn.takeaway.order.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.enums.RefundState;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("`refund_log`")
public class RefundLog implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long adminId;

    private Long orderId;

    private Long userId;

    private PaymentWay paymentWay;

    private Integer allPrice;

    private Integer refundMoney;

    @Length(max = 254)
    private String rejectMsg;

    private RefundState state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;
}
