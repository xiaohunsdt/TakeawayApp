package net.novaborn.takeaway.coupon.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import net.novaborn.takeaway.coupon.enums.CouponType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("coupon_template")
public class CouponTemplate extends Model<CouponTemplate> {
    private static final long serialVersionUID = 7938277365634145592L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @NotBlank(message = "名称不能为空")
    private String couponName;

    private CouponType couponType;

    private Integer couponMoney;

    private Integer couponDiscount;

    /**
     * 最小消费金额
     */
    private Integer minimumMoney;

    /**
     * 可使用时间
     */
    private Integer expireDays;

    /**
     * 允许的分类
     */
    private String allowCategory;

    /**
     * 限制的分类
     */
    private String limitCategory;

    /**
     * 允许的商品
     */
    private String allowGoods;

    /**
     * 限制的商品
     */
    private String limitGoods;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;
}
