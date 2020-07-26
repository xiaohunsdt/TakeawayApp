package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.task.WechatAutoTask;
import net.novaborn.takeaway.admin.utils.OrderSmsUtil;
import net.novaborn.takeaway.admin.web.wrapper.OrderDetailWrapper;
import net.novaborn.takeaway.admin.web.wrapper.OrderWrapper;
import net.novaborn.takeaway.admin.web.wrapper.OrderWrapperEx;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.mq.sender.OrderSignInSender;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    private OrderService orderService;

    private OrderItemService orderItemService;

    private OrderSmsUtil orderSmsUtil;

    private WechatAutoTask wechatAutoTask;

    private OrderSignInSender orderSignInSender;

    @ResponseBody
    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        // 根据昵称获取订单
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<String> ids = userService.getByNickName((String) args.get("nickName")).stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }

        if (StrUtil.isNotBlank((String) args.get("paymentWay"))) {
            args.put("paymentWay", (PaymentWay.valueOf((String) args.get("paymentWay"))).getCode());
        }

        if (StrUtil.isNotBlank((String) args.get("orderState"))) {
            args.put("orderState", (OrderState.valueOf((String) args.get("orderState"))).getCode());
        }

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
    public ResponseEntity getOrderDetail(@RequestParam String orderId) {
        Optional<Order> order = orderService.getById(orderId, true);
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @ResponseBody
    @RequestMapping("getWaitingReceiveOrderCount")
    public int getWaitingReceiveOrderCount(@RequestParam(required = false) DeliveryType deliveryType) {
        return orderService.getWaitingReceiveOrderCount(deliveryType);
    }

    @ResponseBody
    @PostMapping("editOrder")
    public Tip editOrder(@ModelAttribute Order order) {
        Optional<Order> targetOrder = Optional.ofNullable(orderService.getById(order.getId()));
        targetOrder.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (targetOrder.get().getOrderState() == OrderState.FINISHED || targetOrder.get().getOrderState() == OrderState.REFUND || targetOrder.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        if (order.getDiscount() != null && !order.getDiscount().equals(targetOrder.get().getDiscount())) {
            orderService.setDiscount(targetOrder.get(), orderItemService.selectByOrderId(targetOrder.get().getId()), order.getDiscount());
        }
        BeanUtil.copyProperties(order, targetOrder.get(), CopyOptions.create().setIgnoreNullValue(true));

        if (!targetOrder.get().updateById()) {
            return new ErrorTip(-1, "操作失败!");
        }
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("confirmPay")
    public Tip confirmPay(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() != PayState.UN_PAY || order.get().getOrderState() != OrderState.WAITING_RECEIVE) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setPayState(PayState.PAID);
        if (!order.get().updateById()) {
            return new ErrorTip(-1, "操作失败!");
        }
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("receiveOrder")
    public Tip receiveOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() == PayState.UN_PAY || order.get().getOrderState() != OrderState.WAITING_RECEIVE) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.PRODUCING);
        if (!order.get().updateById()) {
            return new ErrorTip(-1, "操作失败!");
        }

        orderSmsUtil.pushMessage(order.get());
        wechatAutoTask.orderShow(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deliveryOrder")
    public Tip deliveryOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() != OrderState.PRODUCING) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.DELIVERING);
        if (!order.get().updateById()) {
            return new ErrorTip(-1, "操作失败!");
        }

        orderSmsUtil.pushMessage(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("finishOrder")
    public Tip finishOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() == OrderState.FINISHED
                || order.get().getOrderState() == OrderState.REFUND
                || order.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.FINISHED);
        if (!order.get().updateById()) {
            return new ErrorTip(-1, "操作失败!");
        }

        orderSignInSender.send(order.get());
        orderSmsUtil.pushMessage(order.get());
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("refundOrder")
    public Tip refundOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getPayState() == PayState.UN_PAY
                || order.get().getOrderState() == OrderState.REFUND
                || order.get().getOrderState() == OrderState.EXPIRED) {
            throw new SysException(OrderExceptionEnum.ORDER_STATE_ERROR);
        }

        order.get().setOrderState(OrderState.REFUND);
        if (!order.get().updateById()) {
            // 恢复库存
            orderItemService.selectByOrderId(orderId).parallelStream().forEach(item -> {
                Optional<Goods> goods = Optional.ofNullable(goodsService.getById(item.getGoodsId()));
                if (goods.isEmpty()) {
                    return;
                }
                goodsStockService.recoverStock(goods.get(), item.getGoodsCount());
            });
            return new ErrorTip(-1, "操作失败!");
        }
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deleteOrder")
    public Tip deleteOrder(@RequestParam String orderId) {
        if (!orderService.removeById(orderId)) {
            return new ErrorTip(-1, "删除失败!");
        }
        return new SuccessTip();
    }
}
