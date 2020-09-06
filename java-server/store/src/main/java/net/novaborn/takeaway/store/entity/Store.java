package net.novaborn.takeaway.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.store.enums.PaymentWay;
import net.novaborn.takeaway.store.enums.State;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@TableName("`store`")
public class Store extends Model<Store> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @NotBlank(message = "店名必须设置")
    private String name;

    @NotNull(message = "计费方式不能为空")
    private PaymentWay paymentWay;

    /**
     * 到期时间，当 PaymentWay 为 MONTH 时可用
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireDate;

    @TableField(value = "`state`")
    private State state;

    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;
}
