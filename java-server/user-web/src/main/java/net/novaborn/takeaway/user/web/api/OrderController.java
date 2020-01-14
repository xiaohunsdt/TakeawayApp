package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.mq.OrderPayExpiredSender;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.dto.OrderDto;
import net.novaborn.takeaway.user.web.wrapper.DeliveryArriveTimeWrapper;
import net.novaborn.takeaway.user.web.wrapper.OrderDetailWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private OrderItemService orderItemService;

    private OrderPayExpiredSender orderPayExpiredSender;

    private JwtTokenUtil jwtTokenUtil;

    @ResponseBody
    @PostMapping("selectOrderById")
    public ResponseEntity selectOrderById(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));
        return ResponseEntity.ok(new OrderDetailWrapper(order.get()).warp());
    }

    @ResponseBody
    @PostMapping("getOrderListByPage")
    public ResponseEntity getOrderListByPage(
            @ModelAttribute Page page,
            @RequestParam(required = false) String orderState) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Map<String, Object> args = new HashMap<>();
        args.put("userId", user.get().getId());
        if (orderState != null) {
            args.put("orderState", orderState);
        }

        page.setOptimizeCountSql(false);
        page = (Page) orderService.getOrderListByPageU(page, args);
        page.setRecords((List) new OrderDetailWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("getOrderCountByState")
    public ResponseEntity getOrderCountByState(String orderState) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Map<String, Object> args = new HashMap<>();
        args.put("userId", user.get().getId());
        if (orderState != null) {
            args.put("orderState", orderState);
        }

        int count = orderService.getOrderCountByStateU(args);
        return ResponseEntity.ok(count);
    }

    @ResponseBody
    @PostMapping("confirmGetOrder")
    public Tip confirmGetOrder(@RequestParam String orderId) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() == OrderState.FINISHED) {
            throw new SysException(OrderExceptionEnum.ORDER_HAVE_FINISHED);
        }

        order.get().setOrderState(OrderState.FINISHED);
        order.get().updateById();
        return new SuccessTip();
    }

    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    @PostMapping("createOrder")
    public Tip createOrder(@RequestBody @Validated OrderDto orderDto) {
        Order order = orderDto.getOrder();
        List<OrderItem> orderItems = orderDto.getOrderItems();

        if (orderItems.size() == 0) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_EXIST);
        }

        //检测订单商品项是否可以下单
        orderItemService.checkOrderItems(orderItems);

        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        //填写订单信息
        int number = orderService.getOrderCountToday() + 1;
        order.setNumber(number);
        order.setUserId(user.get().getId());

        //设置折扣
        orderService.setDiscount(order, orderItems, 85);

        // 10000 以下不配送
        if (order.getRealPrice() < 10000) {
            SysException sysException = new SysException(OrderExceptionEnum.ORDER_BELOW_LOWEST_DELIVERY_PRICE);
            sysException.setMessage("今日85折,低于10000韩币无法配送!!");
            throw sysException;
        }

        //设置订单的支付状态
        if (order.getPaymentWay() == PaymentWay.CREDIT_CARD || order.getPaymentWay() == PaymentWay.CASH) {
            //刷卡和现金支付设置为后付状态
            order.setPayState(PayState.PAY_LATER);
        } else {
            order.setPayState(PayState.UN_PAY);
        }

        //先生成订单，再生成订单产品详情
        if (order.insert()) {
            orderItems.parallelStream().forEach(item -> {
                item.setOrderId(order.getId());
                item.setGoodsThumb(URLUtil.getPath(item.getGoodsThumb()));
                item.insert();
            });
        }

        //将未支付的订单丢给订单过期队列
        if (order.getPayState() == PayState.UN_PAY) {
            orderPayExpiredSender.send(order, 15 * 60);
        }

        //将订单id返回
        return new SuccessTip(order.getId());
    }

    @ResponseBody
    @PostMapping("createComment")
    public Tip createComment(@ModelAttribute @Validated Comment comment) {
        Optional<Order> order = Optional.ofNullable(orderService.getById(comment.getOrderId()));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        if (order.get().getOrderState() != OrderState.FINISHED) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_FINISHED);
        }

        comment.setUserId(order.get().getUserId());

        if (!comment.insert()) {
            return new ErrorTip(-1, "评论失败!");
        }

        order.get().setIsCommented(true);
        order.get().updateById();

        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deleteOrder")
    public Tip deleteOrder(@RequestParam String orderId) {
        if (!orderService.removeById(orderId)) {
            return new ErrorTip(-1, "删除失败!");
        }

        return new SuccessTip("删除成功!");
    }

    @ResponseBody
    @PostMapping("getDeliveryArriveTime")
    public DeliveryArriveTimeWrapper getDeliveryArriveTime(@RequestParam String orderId) {
//        orderService.getOrderCountByStateU()
        return new DeliveryArriveTimeWrapper(null);
    }
}
