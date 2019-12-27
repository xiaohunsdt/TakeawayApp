package net.novaborn.takeaway.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

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

    
}
