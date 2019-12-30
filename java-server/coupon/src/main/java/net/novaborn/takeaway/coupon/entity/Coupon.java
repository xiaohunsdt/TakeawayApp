package net.novaborn.takeaway.coupon.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.enums.CouponType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("coupon")
public class Coupon extends Model<Coupon> {
    private static final long serialVersionUID = 7938677365637245592L;

    @TableId(type = IdType.UUID)
    private String id;

    private String userId;

    private String couponName;

    private CouponType couponType;

    private Integer couponMoney;

    private Integer couponDiscount;

    /**
     * 最小金额
     */
    private Integer minimumMoney;

    /**
     * 可使用时间
     */
    private Date expireDate;

    /**
     * 限制的分类
     */
    private String limitCategory;

    /**
     * 限制的商品
     */
    private String limitGoods;

    private CouponState state;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
