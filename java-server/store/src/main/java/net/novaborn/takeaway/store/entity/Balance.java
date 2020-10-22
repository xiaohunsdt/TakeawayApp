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
@TableName("`store_balance`")
public class Balance extends Model<Balance> {

    @TableId(type = IdType.ASSIGN_ID)
    private Long storeId;

    private Long money;

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
