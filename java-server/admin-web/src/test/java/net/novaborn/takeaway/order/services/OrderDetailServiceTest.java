package net.novaborn.takeaway.order.services;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.order.service.impl.OrderDetailService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class OrderDetailServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

//    @Test
//    public void getOrderCountTodayTest() {
//        orderService.list().forEach(item -> {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrderId(item.getId());
//            orderDetail.setAppointmentDate(item.getAppointmentDate());
//            orderDetail.setPs(item.getPs());
//            orderDetail.setFrom(item.getFrom());
//            orderDetail.setIsCommented(item.getIsCommented());
//            orderDetailService.save(orderDetail);
//
//            if (item.getAppointmentDate() != null) {
//                item.setOrderType(OrderType.APPOINTMENT);
//            } else {
//                item.setOrderType(OrderType.NORMAL);
//            }
//            orderService.updateById(item);
//        });
//    }
}
