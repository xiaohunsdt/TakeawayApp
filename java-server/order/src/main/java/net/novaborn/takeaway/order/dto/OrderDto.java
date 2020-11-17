package net.novaborn.takeaway.order.dto;

import lombok.Data;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.entity.OrderItem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderDto {

    @Valid
    @NotNull(message = "没有订单")
    private Order order;

    @Valid
    @NotNull(message = "没有订单详细")
    private OrderDetail orderDetail;

    @Valid
    @Size(min = 1, message = "没有订单产品信息")
    @NotNull(message = "没有订单产品信息")
    private List<OrderItem> orderItems;

    private Long couponId;
}
