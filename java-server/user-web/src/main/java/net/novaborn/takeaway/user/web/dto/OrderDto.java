package net.novaborn.takeaway.user.web.dto;

import lombok.Data;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;

import java.util.List;

@Data
public class OrderDto {
    private Order order;
    private List<OrderItem> orderItems;
}
