package net.novaborn.takeaway.order.services;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.DeliveryType;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.statistics.entity.UserConsumption;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    AddressService addressService;

    @Test
    public void getOrderCountTodayTest() {
        System.out.println(orderService.getOrderCount(new Date(), DeliveryType.NORMAL));
    }

    @Test
    public void getOrderCountByStateUTest() {
        System.out.println(orderService.getOrderCountByStateU(null, OrderStateEx.WAIT_EAT));
    }

    @Test
    public void getTodayOrderCountByStateUTest() {
        System.out.println(orderService.getTodayOrderCountByStateU(null, OrderStateEx.WAIT_EAT));
    }

    @Test
    public void getGoodsSaleTest() {
        List<Order> orderList = orderService.list().stream().filter(order -> order.getOrderState() == OrderState.FINISHED).collect(Collectors.toList());
        System.out.println(orderService.getGoodsSales(orderList));
    }

    @Test
    public void getOrderListByDateTest() {
        Date start = DateUtil.parseDateTime("2020-06-17 00:00:00");
        Date end = DateUtil.parseDateTime("2020-06-17 23:00:00");
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args).parallelStream()
                .filter(order -> DateUtil.isIn(order.getCreateDate(), start, end))
//                .filter(order -> DateUtil.between(order.getCreateDate(), order.getUpdateDate(), DateUnit.MINUTE) >= 60)
                .collect(Collectors.toList());
        orderList.forEach(order -> System.out.println(order.getUserId()));
        System.out.println(orderList.stream()
                .map(order -> addressService.getById(order.getAddressId()).getPhone())
                .collect(Collectors.joining(",")));
    }

    @Test
    public void getOrderListByGoodsNameTest() {
        Date start = DateUtil.parseDateTime("2020-06-10 00:00:00");
        Date end = DateUtil.parseDateTime("2020-06-11 00:00:00");
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args).parallelStream()
                .filter(order -> {
                    List<OrderItem> orderItems = orderItemService.selectByOrderId(order.getId());
                    boolean isExist = false;
                    for (OrderItem orderItem : orderItems) {
                        if (orderItem.getGoodsName().contains("鸭脖") || orderItem.getGoodsName().contains("鸭锁骨")) {
                            isExist = true;
                            break;
                        }
                    }

                    return isExist;
                })
                .collect(Collectors.toList());

        System.out.println("订单号：");
        orderList.stream().forEach(order -> System.out.println(order.getId()));
        System.out.println("用户：");
        orderList.stream().forEach(order -> System.out.println(order.getUserId()));
        System.out.println(orderList.stream()
                .map(order -> addressService.getById(order.getAddressId()).getPhone())
                .collect(Collectors.joining(",")));
    }

    @Test
    public void orderReportForPersonal() {
        Date start = DateUtil.parseDateTime("2020-05-01 00:00:00");
        Date end = DateUtil.parseDateTime("2020-05-31 23:59:59");
        long days = DateUtil.dayOfMonth(end);
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args);
        List<UserConsumption> userConsumptionList = orderList.stream().map(order -> new UserConsumption(order.getUserId())).distinct().collect(Collectors.toList());

        Map<String, UserConsumption> tempMap = new HashMap<>();
        userConsumptionList.forEach(userConsumption -> tempMap.put(userConsumption.getUserId(), userConsumption));

        orderList.parallelStream().forEach(order -> {
            UserConsumption userConsumption = tempMap.get(order.getUserId());
            userConsumption.setTotalOrderCount(userConsumption.getTotalOrderCount() + 1);
            userConsumption.setTotalOrderItemCount(userConsumption.getTotalOrderItemCount() + order.getGoodsCount());
            userConsumption.setTotalOrderPrice(userConsumption.getTotalOrderPrice() + order.getRealPrice());
        });

        userConsumptionList.parallelStream().forEach(userConsumption -> {
            userConsumption.setAverageDailyPrice(userConsumption.getTotalOrderPrice() / (int) days);
            userConsumption.setAverageOrderPrice(userConsumption.getTotalOrderPrice() / userConsumption.getTotalOrderCount());
        });

        userConsumptionList.sort(Comparator.comparingInt(UserConsumption::getTotalOrderCount).reversed());
        userConsumptionList.forEach(System.out::println);

//        userConsumptionList.sort(Comparator.comparingInt(UserConsumption::getTotalOrderPrice).reversed());
//        userConsumptionList.forEach(System.out::println);
    }
}

