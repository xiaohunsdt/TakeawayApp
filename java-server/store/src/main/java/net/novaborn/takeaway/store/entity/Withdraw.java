package net.novaborn.takeaway.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import net.novaborn.takeaway.store.enums.WithdrawState;

import java.util.Date;

@Data
@TableName("`store_withdraw`")
public class Withdraw {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long storeId;

    private Long money;

    private Long fee;

    private String ps;

    private WithdrawState state;

    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;
}
