package net.novaborn.takeaway.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("coupon_log")
public class CouponLog extends Model<CouponLog> {
    private static final long serialVersionUID = 7938677365637245590L;

    @TableId(type = IdType.UUID)
    private String id;

    private String couponId;

    private String orderId;

    private Integer couponAmount;

    private Integer orderOriginalAmount;

    private Integer orderFinalAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date useDate;
}
