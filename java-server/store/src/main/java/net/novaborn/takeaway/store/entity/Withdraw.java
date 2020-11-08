package net.novaborn.takeaway.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.store.enums.WithdrawState;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@TableName("`store_withdraw`")
public class Withdraw extends Model<Withdraw> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long storeId;

    @Min(value = 5000, message = "申请金额必须大于或等于5000")
    private Long money;

    private Long fee;

    @NotBlank(message = "银行必须选择")
    private String bankName;

    @NotBlank(message = "账户名不能为空")
    private String accountName;

    @NotBlank(message = "账户不能为空")
    private String account;

    private String ps;

    private WithdrawState state;

    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @Version
    @JSONField(serialize = false)
    private Integer version;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;
}
