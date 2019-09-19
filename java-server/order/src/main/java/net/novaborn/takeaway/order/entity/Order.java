package net.novaborn.takeaway.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author xiaohun
 * @since 2018-07-12
 */
@Data
@TableName("order")
public class Order extends Model<Order> {

}
