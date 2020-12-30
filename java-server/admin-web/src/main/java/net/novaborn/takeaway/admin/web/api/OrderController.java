package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.task.WechatAutoTask;
import net.novaborn.takeaway.admin.utils.PrinterUtil;
import net.novaborn.takeaway.admin.web.wrapper.OrderDetailWrapper;
import net.novaborn.takeaway.admin.web.wrapper.OrderWrapper;
import net.novaborn.takeaway.admin.web.wrapper.OrderWrapperEx;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.mq.sender.OrderSignInSender;
import net.novaborn.takeaway.mq.sender.OrderSubscribeMessageSender;
import net.novaborn.takeaway.mq.sender.WxPayRefundSender;
import net.novaborn.takeaway.order.entity.Delivery;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.entity.RefundLog;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.DeliveryService;
import net.novaborn.takeaway.order.service.impl.OrderDetailService;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.order.service.impl.RefundLogService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/order")
public class OrderController extends BaseController {

    private UserService userService;

    private OrderService orderService;

    private OrderDetailService orderDetailService;

    private RefundLogService refundLogService;

    private OrderItemService orderItemService;

    private SettingService settingService;

    private DeliveryService deliveryService;

    private WechatAutoTask wechatAutoTask;

    private OrderAutoReceiveSender orderAutoReceiveSender;

    private OrderSignInSender orderSignInSender;

    private OrderSubscribeMessageSender orderSubscribeMessageSender;

    private WxPayRefundSender wxPayRefundSender;

    private PrinterUtil printerUtil;

    private JwtTokenUtil jwtTokenUtil;

    @ResponseBody
    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args, PaymentWay paymentWay, OrderType orderType, OrderState orderState) {
        // 根据昵称获取订单
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<Long> ids = userService.getByNickName((String) args.get("nickName")).stream()
                .map(User::getId)
                .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }

        args.put("paymentWay", paymentWay);
        args.put("orderType", orderType);
        args.put("orderState", orderState);

        page = (Page) orderService.getOrderListByPage(page, args);
        page.setRecords((List) new OrderWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("getTodayOrderListByState")
    public ResponseEntity getTodayOrderListByState(OrderStateEx orderState) {
        List<Order> orderList = orderService.getTodayOrderByStateU(null, orderState);
        return ResponseEntity.ok(new OrderWrapperEx(orderList).warp());
    }

    @ResponseBody
    @PostMapping("getTodayOrderList")
    public ResponseEntity getTodayOrderList() {
        List<Order> orderList = orderService.getTodayOrderByStateU(null, null).stream()
            .filter(order -> order.getOrderState() != OrderState.REFUND && order.getPayState() != PayState.UN_PAY)
            .collect(Collectors.toList());
        return ResponseEntity.ok(new OrderWrapperEx(orderList).warp());
    }

    @ResponseBody
    @PostMapping("getOrderDetail")
    public ResponseEntity getOrderDetail(@RequestParam Long orderId) {
        Optional<Order> order = orderService.getById(orderId, true);
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @ResponseBody
    @RequestMapping("getWaitingReceiveOrderCount")
    public int getWaitingReceiveOrderCount(@RequestParam(required = false) OrderType orderType) {
        return orderService.getWaitingReceiveOrderCount(orderType);
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("editOrder")
    public Tip editOrder(Long id, Short discount, String ps) {
        Optional<Order> targetOrder = Optional.ofNullable(orderService.getById(id));
        targetOrder.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (targetOrder.get().getOrderState() == OrderState.FINISHED || targetOrder.get().getOrderState() == OrderState.REFUND || targetOrder.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        if (discount != null && !discount.equals(targetOrder.get().getDiscount())) {
            orderService.setDiscount(targetOrder.get(), orderItemService.selectByOrderId(targetOrder.get().getId()), discount);
        }

        if(StrUtil.isNotBlank(ps)){
            OrderDetail detail = orderDetailService.getById(id);
            detail.setPs(ps);
            orderDetailService.updateById(detail);
        }

        orderService.updateById(targetOrder.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("confirmPay")
    public Tip confirmPay(@RequestParam Long orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() != PayState.UN_PAY || order.get().getOrderState() != OrderState.WAITING_RECEIVE) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setPayState(PayState.PAID);
        if (!orderService.updateById(order.get())) {
            return new ErrorTip(-1, "操作失败!");
        }

        // 系统是否允许自动接单
        Setting orderAutoReceive = settingService.getSettingByName("auto_receive_order", SettingScope.SYSTEM);
        if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
            orderAutoReceiveSender.send(order.get());
        }

        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("receiveOrder")
    public Tip receiveOrder(@RequestParam Long orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() == PayState.UN_PAY || order.get().getOrderState() != OrderState.WAITING_RECEIVE) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.PRODUCING);
        orderService.updateById(order.get());

        orderSubscribeMessageSender.send(order.get());
        printerUtil.print(order.get());
        wechatAutoTask.orderShow(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deliveryOrder")
    public Tip deliveryOrder(@RequestParam Long orderId) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);

        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() != OrderState.PRODUCING) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.DELIVERING);
        if (!orderService.updateById(order.get())) {
            return new ErrorTip(-1, "操作失败!");
        }

        // 添加配送信息类
        Delivery delivery = new Delivery();
        delivery.setAdminId(Long.parseLong(adminId));
        delivery.setOrderId(orderId);
        delivery.setNumber(order.get().getNumber());
        delivery.setMoney(order.get().getRealPrice());
        delivery.setPaymentWay(order.get().getPaymentWay());
        delivery.setOrderCreateDate(order.get().getCreateDate());
        delivery.insert();

        orderSubscribeMessageSender.send(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("finishOrder")
    @Transactional(rollbackFor = RuntimeException.class)
    public Tip finishOrder(@RequestParam Long orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() == OrderState.FINISHED
            || order.get().getOrderState() == OrderState.REFUND
            || order.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.FINISHED);
        if (!orderService.updateById(order.get())) {
            return new ErrorTip(-1, "操作失败!");
        }

        // 签到
        if (order.get().getPaymentWay() != PaymentWay.CREDIT_CARD && order.get().getRealPrice() >= 12000) {
            orderSignInSender.send(order.get());
        }

        // 设置配送信息
        Optional<Delivery> delivery = deliveryService.getByOrderId(orderId);
        delivery.get().setFinishDate(new Date());
        deliveryService.updateById(delivery.get());

        orderSubscribeMessageSender.send(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("refundOrder")
    @Transactional(rollbackFor = Exception.class)
    public Tip refundOrder(@RequestParam Long orderId, @RequestParam Integer money, @RequestParam String refundRes) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() == PayState.UN_PAY
            || order.get().getOrderState() == OrderState.REFUND
            || order.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        int canRefundMoney = order.get().getRealPrice() - refundLogService.getAllRefundMoneyByOrderId(orderId);
        if (canRefundMoney < money) {
            return new ErrorTip(-1, "退款金额超出最大允许值!");
        }

        RefundLog refundLog = new RefundLog();
        refundLog.setOrderId(orderId);
        refundLog.setAdminId(Long.parseLong(jwtTokenUtil.getUserIdFromToken(request)));
        refundLog.setUserId(order.get().getUserId());
        refundLog.setPaymentWay(order.get().getPaymentWay());
        refundLog.setAllPrice(order.get().getRealPrice());
        refundLog.setRefundMoney(money);
        if(StrUtil.isNotBlank(refundRes)){
            refundLog.setRefundRes(refundRes);
        }

        boolean result = refundLogService.save(refundLog);
        if (!result) {
            return new ErrorTip(-1, "添加退款记录失败!");
        }

        if (order.get().getPaymentWay() == PaymentWay.WEIXIN_PAY) {
            wxPayRefundSender.send(refundLog);
        }

        if (money.equals(canRefundMoney)) {
            order.get().setOrderState(OrderState.REFUND);
        } else {
            order.get().setOrderState(OrderState.PART_REFUND);
        }

        if (orderService.updateById(order.get())) {
            // 恢复库存
//            orderItemService.selectByOrderId(orderId).parallelStream().forEach(item -> {
//                Optional<Goods> goods = Optional.ofNullable(goodsService.getById(item.getGoodsId()));
//                if (goods.isEmpty()) {
//                    return;
//                }
//                goodsStockService.recoverStock(goods.get(), item.getGoodsCount());
//            });
            return new SuccessTip();
        } else {
            return new ErrorTip(-1, "操作失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteOrder")
    public Tip deleteOrder(@RequestParam Long orderId) {
        if (!orderService.removeById(orderId)) {
            return new ErrorTip(-1, "删除失败!");
        }
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("printOrder")
    public Tip printOrder(@RequestParam Long orderId) {
        printerUtil.print(orderService.getById(orderId));
        return new SuccessTip();
    }
}
