package net.novaborn.takeaway.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.store.enums.WithdrawState;

import java.util.Date;

@Data
@TableName("`store_withdraw`")
public class Withdraw extends Model<Withdraw> {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long storeId;

    private Integer money;

    private Integer fee;

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
