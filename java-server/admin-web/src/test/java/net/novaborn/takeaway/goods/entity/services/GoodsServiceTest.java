package net.novaborn.takeaway.goods.entity.services;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class GoodsServiceTest {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;
}

