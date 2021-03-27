package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.mq.sender.OrderPayExpiredSender;
import net.novaborn.takeaway.order.dto.OrderDto;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderDetailService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.dto.DeliveryArriveTimeDto;
import net.novaborn.takeaway.user.web.wrapper.OrderDetailWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/order")
public class OrderController extends BaseController {
    private UserService userService;

    private OrderService orderService;

    private OrderDetailService orderDetailService;

    private SettingService settingService;

    private OrderPayExpiredSender orderPayExpiredSender;

    private OrderAutoReceiveSender orderAutoReceiveSender;

    private JwtTokenUtil jwtTokenUtil;

    @ResponseBody
    @PostMapping("selectOrderById")
    public ResponseEntity selectOrderById(@RequestParam Long orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @ResponseBody
    @PostMapping("selectOrderDetailById")
    public ResponseEntity selectOrderDetailById(@RequestParam Long orderId) {
        Optional<OrderDetail> orderDetail = Optional.ofNullable(orderDetailService.getById(orderId));
        orderDetail.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_DETAIL_NOT_EXIST));
        return ResponseEntity.ok(orderDetail.get());
    }

    @ResponseBody
    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(@ModelAttribute Page page, @RequestParam(required = false) OrderStateEx orderState) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        page.setOptimizeCountSql(false);
        page = (Page) orderService.getOrderListByPageU(page, user.get().getId(), orderState);
        page.setRecords((List) new OrderDetailWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("getOrderCountByState")
    public ResponseEntity getOrderCountByState(OrderStateEx orderState) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        int count = orderService.getOrderCountByStateU(user.get().getId(), orderState);
        return ResponseEntity.ok(count);
    }

    @ResponseBody
    @PostMapping("confirmGetOrder")
    public Tip confirmGetOrder(@RequestParam Long orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() == OrderState.FINISHED) {
            throw new SysException(OrderExceptionEnum.ORDER_HAVE_FINISHED);
        }

        order.get().setOrderState(OrderState.FINISHED);
        orderService.updateById(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("createOrder")
    public Tip createOrder(@RequestBody @Validated OrderDto orderDto) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Order order = orderDto.getOrder();
        order.setUserId(user.get().getId());

        if (order.getOrderType() == OrderType.NORMAL && !this.getCanOrderNow()) {
            throw new SysException(OrderExceptionEnum.ORDER_CAN_NOT_CREATE_FOR_NOW);
        }

        // 生成订单
        order = orderService.createOrder(orderDto);

        // 判断订单的状态并处理
        // 将未支付的订单丢给订单过期队列
        if (order.getPayState() == PayState.UN_PAY) {
            orderPayExpiredSender.send(order, 30 * 60);
        } else {
            // 系统是否允许自动接单
            Setting orderAutoReceive = settingService.getSettingByName("auto_receive_order", SettingScope.SYSTEM);
            if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
                orderAutoReceiveSender.send(order.getId());
            }
        }

        //将订单id返回
        return new SuccessTip(order.getId());
    }

    @ResponseBody
    @PostMapping("createComment")
    public Tip createComment(@ModelAttribute @Validated Comment comment) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(comment.getOrderId()));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        Optional<OrderDetail> orderDetail = Optional.ofNullable(orderDetailService.getById(comment.getOrderId()));
        orderDetail.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_DETAIL_NOT_EXIST));

        if (order.get().getOrderState() != OrderState.FINISHED) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_FINISHED);
        }

        comment.setUserId(order.get().getUserId());

        if (!comment.insert()) {
            return new ErrorTip(-1, "评论失败!");
        }

        orderDetail.get().setIsCommented(true);
        orderDetailService.updateById(orderDetail.get());

        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deleteOrder")
    public Tip deleteOrder(@RequestParam Long orderId) {
        if (!orderService.removeById(orderId)) {
            return new ErrorTip(-1, "删除失败!");
        }

        return new SuccessTip("删除成功!");
    }

    @RequestMapping("getCanOrderNow")
    @ResponseBody
    public Boolean getCanOrderNow() {
        Date now = new Date();
        String store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();
        String store_open_time = settingService.getSettingByName("store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName("store_close_time", SettingScope.STORE).getValue();

        DateTime currentDate = DateUtil.date();
        DateTime storeOpenTime = DateUtil.parseDateTime(store_open_time)
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        DateTime storeCloseTime = DateUtil.parseDateTime(store_close_time)
                .setField(DateField.YEAR, currentDate.getField(DateField.YEAR))
                .setField(DateField.MONTH, currentDate.getField(DateField.MONTH))
                .setField(DateField.DAY_OF_MONTH, currentDate.getField(DateField.DAY_OF_MONTH));
        if (storeOpenTime.isAfter(storeCloseTime)) {
            if (currentDate.isBefore(storeCloseTime)) {
                storeOpenTime.offset(DateField.DAY_OF_YEAR, -1);
            } else {
                storeCloseTime.offset(DateField.DAY_OF_YEAR, 1);
            }
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(now)))) {
            return false;
        }
        return DateUtil.isIn(currentDate, storeOpenTime, storeCloseTime);
    }

    @ResponseBody
    @PostMapping("getDeliveryArriveTime")
    public Object getDeliveryArriveTime(@RequestParam(required = false) Long orderId) {
        Date deliveryDate;
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        Optional<OrderDetail> orderDetail = Optional.ofNullable(orderDetailService.getById(orderId));

        int base_express_time = Integer.parseInt(settingService.getSettingByName("base_express_time", SettingScope.DELIVERY).getValue());
        int average_express_time = Integer.parseInt(settingService.getSettingByName("average_express_time", SettingScope.DELIVERY).getValue());
        int deliverier_count = Integer.parseInt(settingService.getSettingByName("deliverier_count", SettingScope.DELIVERY).getValue());

        Date current = new Date();
        List<Order> orderList = orderService.getTodayOrderByStateU(null, OrderStateEx.WAIT_EAT).stream()
                .filter(item -> item.getOrderType() == OrderType.NORMAL || item.getOrderType() == OrderType.APPOINTMENT)
                .filter(item -> {
                    // 返回今天的要配送的订单
                    Date target;
                    if (item.getOrderType() == OrderType.NORMAL) {
                        target = item.getCreateDate();
                    } else {
                        target = orderDetailService.getById(item.getId()).getAppointmentDate();
                        item.setCreateDate(target);
                    }
                    return DateUtil.isSameDay(target, current);
                })
                .sorted((o1, o2) -> {
                    Date dateForO1 = o1.getCreateDate();
                    Date dateForO2 = o2.getCreateDate();
                    return dateForO1.compareTo(dateForO2);
                })
                .collect(Collectors.toList());

        int index;
        if (order.isPresent()) {
            if (orderDetail.get().getAppointmentDate() != null) {
                return new DeliveryArriveTimeDto(orderDetail.get().getAppointmentDate());
            }
            if (order.get().getPayState() == PayState.UN_PAY) {
                return new SuccessTip("未知");
            }

            if (order.get().getOrderState() == OrderState.FINISHED) {
                return new SuccessTip("已完成");
            }

            if (order.get().getOrderState() == OrderState.REFUND) {
                return new SuccessTip("已退款");
            }

            index = orderList.indexOf(order.get());
        } else {
            index = orderList.size() - 1;
        }

        if (index == 0) {
            deliveryDate = DateUtil.offsetMinute(new Date(), base_express_time);
        } else {
            deliveryDate = DateUtil.offsetMinute(new Date(), (base_express_time + index * average_express_time) / deliverier_count);
        }
        return new DeliveryArriveTimeDto(deliveryDate);
    }
}
