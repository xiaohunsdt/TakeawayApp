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

    @Test
    public void getGoodsSale() {
//        List<Goods> goodsList = goodsService.list();
        TreeMap<String, Integer> goodsSale = new TreeMap<>();

        orderService.list().stream()
                .filter(order -> order.getOrderState() == OrderState.FINISHED)
                .forEach(order -> {
                    orderItemService.selectByOrderId(order.getId()).forEach(orderItem -> {
                        Integer count = orderItem.getGoodsCount();
                        if (goodsSale.containsKey(orderItem.getGoodsName())) {
                            count += goodsSale.get(orderItem.getGoodsName());
                        }
                        goodsSale.put(orderItem.getGoodsName(), count);
                    });
                });

        List<Map.Entry<String,Integer>> list = new ArrayList<>(goodsSale.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        System.out.println(list);

    }
}

