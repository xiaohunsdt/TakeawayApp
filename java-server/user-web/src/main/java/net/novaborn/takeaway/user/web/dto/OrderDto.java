package net.novaborn.takeaway.user.web.dto;

import lombok.Data;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderDto {

    @NotNull(message = "没有订单信息")
    @Valid
    private Order order;

    @Size(min = 1, message = "没有订单产品信息")
    private List<OrderItem> orderItems;
}
