package net.novaborn.takeaway.order.services;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.statistics.entity.UserConsumption;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
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
    public void getOrderCountTest() {
        System.out.println(orderService.getOrderCount(new Date(), OrderType.NORMAL));
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
        Date start = DateUtil.parseDateTime("2019-11-22 00:00:00");
        Date end = DateUtil.parseDateTime("2020-10-30 23:00:00");

        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));

        Set<Long> userIds = new CopyOnWriteArraySet<>();
        List<Order> orderList = orderService.getOrderList(args).stream().sorted(Comparator.comparing(Order::getCreateDate)).collect(Collectors.toList());
        System.out.println(orderList.size());
        orderList = orderList.parallelStream()
            .filter(order -> userIds.add(order.getUserId()))
//                .filter(order -> DateUtil.between(order.getCreateDate(), order.getUpdateDate(), DateUnit.MINUTE) >= 50)
//                .filter(order -> order.getAppointmentDate() == null)
            .collect(Collectors.toList());

//        orderList.forEach(order -> System.out.println(order.getUserId()));
//        System.out.println(orderList.stream()
//                .map(order -> addressService.getById(order.getAddressId()).getPhone())
//                .collect(Collectors.joining(",")));

        List<String> phones = orderList.stream()
            .map(order -> addressService.getById(order.getAddressId()))
            .distinct()
            .filter(Objects::nonNull)
            .map(Address::getPhone)
            .filter(item -> !item.equals("01000000000"))
            .collect(Collectors.toList());
        System.out.println(phones.size());
        System.out.println(userIds.size());
        System.out.println(orderList.size());

        for (int i = 0; i < phones.size(); i += 20) {
            System.out.println(String.format("%d------------------------", i / 20 + 1));
            int temp = Math.min(i + 20, phones.size());
            System.out.println(String.join(",", phones.subList(i, temp)));
        }
    }

    @Test
    public void getOrderListByGoodsNameTest() {
        Date start = DateUtil.parseDateTime("2020-09-18 00:00:00");
        Date end = DateUtil.parseDateTime("2020-09-18 23:00:00");
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args).parallelStream()
            .filter(order -> {
                List<OrderItem> orderItems = orderItemService.selectByOrderId(order.getId());
                boolean isExist = false;
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getProduceName().contains("鸭脖")
                        || orderItem.getProduceName().contains("鸭锁骨")
                        || orderItem.getProduceName().contains("鸭翅")
                        || orderItem.getProduceName().contains("鸭头")
                        || orderItem.getProduceName().contains("鸭肠")
                        || orderItem.getProduceName().contains("鸭舌")
                        || orderItem.getProduceName().contains("鸭胗")
                    ) {
                        isExist = true;
                        break;
                    }
                }

                return isExist;
            })
            .collect(Collectors.toList());

        System.out.println("订单号：");
        orderList.forEach(order -> System.out.println(order.getId()));
        System.out.println("用户：");
        orderList.forEach(order -> System.out.println(order.getUserId()));
        System.out.println(orderList.stream()
            .map(order -> addressService.getById(order.getAddressId()).getPhone())
            .collect(Collectors.joining(",")));
    }

    @Test
    public void getOrderByProduceIdTest() {
        long produceId = 1318313202157740033L;
        AtomicInteger count = new AtomicInteger();
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        orderService.getOrderList(args).parallelStream()
            .forEach(order -> {
                List<OrderItem> orderItems = orderItemService.selectByOrderId(order.getId());
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getProduceId().equals(produceId)) {
                        System.out.println(orderItem);
                        count.getAndAdd(orderItem.getGoodsCount());
                    }
                }
            });
        System.out.println(count);
    }

    @Test
    public void orderReportForPersonal() {
        Date start = DateUtil.parseDateTime("2020-11-01 00:00:00");
        Date end = DateUtil.parseDateTime("2020-11-14 23:59:59");
        long days = DateUtil.dayOfMonth(end);
        Map<String, Object> args = new HashMap<>();
        args.put("orderState", OrderState.FINISHED.getCode());
        args.put("startDate", DateUtil.formatDateTime(start));
        args.put("endDate", DateUtil.formatDateTime(end));
        List<Order> orderList = orderService.getOrderList(args);
        List<UserConsumption> userConsumptionList = orderList.stream().map(order -> new UserConsumption(order.getUserId())).distinct().collect(Collectors.toList());

        Map<Long, UserConsumption> tempMap = new HashMap<>();
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

