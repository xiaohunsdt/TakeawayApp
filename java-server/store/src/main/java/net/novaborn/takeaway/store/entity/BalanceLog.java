package net.novaborn.takeaway.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.store.constant.EventTypeText;

import java.util.Date;

@Data
@TableName("`store_balance_log`")
public class BalanceLog extends Model<BalanceLog> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long storeId;

    private Integer eventType;

    private Long money;

    private Long afterMoney;

    private String ps;

    private Date createDate;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;

    public void setTypeTextToPs(Integer eventType, Object... args) {
        String ps = EventTypeText.get(eventType);
        this.ps = String.format(ps, args);
    }
}
