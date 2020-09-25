package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.order.enums.PaymentWay;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 外卖员配送类
 */
@Data
@TableName("`delivery`")
public class Delivery extends Model<Delivery> {
    private static final long serialVersionUID = -4520888123754634505L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long adminId;

    private Long orderId;

    private Integer number;

    private PaymentWay paymentWay;

    private Integer money;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderCreateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishDate;
}
