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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("coupon_template")
public class CouponTemplate extends Model<CouponTemplate> {
    private static final long serialVersionUID = 7938277365634145592L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long storeId;

    @NotBlank(message = "名称不能为空")
    private String couponName;

    private CouponType couponType;

    private Integer couponMoney;

    private Integer couponDiscount;

    /**
     * 最小消费金额
     */
    @NotNull(message = "最低消费:必须设置数值!最小为0,表示没有限制!")
    @Min(value = 0, message = "最低消费:最小值为0,表示没有限制!")
    private Integer minimumMoney;

    /**
     * 可使用时间
     */
    @NotNull(message = "过期天数:必须设置数值! 最小为0,表示没有限制!")
    @Min(value = 0, message = "过期天数:最小值为0,表示没有限制!")
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
