package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("order")
public class Order extends Model<Order> {

}
