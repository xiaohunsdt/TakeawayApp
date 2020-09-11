package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.services.impl.CouponLogService;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.mq.sender.OrderAutoReceiveSender;
import net.novaborn.takeaway.mq.sender.OrderPayExpiredSender;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.enums.PayState;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.web.dto.DeliveryArriveTimeDto;
import net.novaborn.takeaway.user.web.dto.OrderDto;
import net.novaborn.takeaway.user.web.wrapper.OrderDetailWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    private CouponService couponService;

    private CouponLogService couponLogService;

    private OrderService orderService;

    private OrderItemService orderItemService;

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
        order.get().updateById();
        return new SuccessTip();
    }

    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("createOrder")
    public Tip createOrder(@RequestBody @Validated OrderDto orderDto) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        user.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Order order = orderDto.getOrder();
        List<OrderItem> orderItems = orderDto.getOrderItems();

        if (orderItems.size() == 0) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_EXIST);
        }

        if (order.getAppointmentDate() == null && !this.getCanOrderNow(order.getStoreId())) {
            throw new SysException(OrderExceptionEnum.ORDER_CAN_NOT_CREATE_FOR_NOW);
        }

        order.setUserId(user.get().getId());

        //检测订单商品项是否可以下单
        orderItemService.checkOrderItems(orderItems);
        orderService.checkOrder(order, orderItems);
        orderService.postCheckOrder(order, orderItems, orderDto.getCouponId());

        //先生成订单，再生成订单产品详情
        if (order.insert()) {
            orderItems.parallelStream().forEach(item -> {
                item.setOrderId(order.getId());
                if (StrUtil.isNotBlank(item.getGoodsThumb())) {
                    item.setGoodsThumb(URLUtil.getPath(item.getGoodsThumb()));
                }
                item.insert();

                // 减少库存
                goodsStockService.reduceStock(goodsService.getById(item.getGoodsId()), item.getGoodsCount());
            });

            // 对优惠卷进行后续处理
            if (orderDto.getCouponId() != null && orderDto.getCouponId() != null) {
                Coupon coupon = couponService.getById(orderDto.getCouponId());
                coupon.setState(CouponState.USED);
                coupon.updateById();

                // 添加优惠卷使用记录
                couponLogService.makeNewCouponLog(order, coupon);
            }
        }

        //将未支付的订单丢给订单过期队列
        if (order.getPayState() == PayState.UN_PAY) {
            orderPayExpiredSender.send(order, 30 * 60);
        } else {
            // 系统是否允许自动接单
            Setting orderAutoReceive = settingService.getSettingByName(order.getStoreId(),"auto_receive_order", SettingScope.SYSTEM);
            if (orderAutoReceive != null && "true".equals(orderAutoReceive.getValue())) {
                orderAutoReceiveSender.send(order);
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

        if (order.get().getOrderState() != OrderState.FINISHED) {
            throw new SysException(OrderExceptionEnum.ORDER_NOT_FINISHED);
        }

        comment.setUserId(order.get().getUserId());
        comment.setStoreId(order.get().getStoreId());

        if (!comment.insert()) {
            return new ErrorTip(-1, "评论失败!");
        }

        order.get().setIsCommented(true);
        order.get().updateById();

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
    public Boolean getCanOrderNow(Long storeId) {
        Date now = new Date();
        String store_open_date = settingService.getSettingByName(storeId, "store_open_date", SettingScope.STORE).getValue();
        String store_open_time = settingService.getSettingByName(storeId, "store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName(storeId, "store_close_time", SettingScope.STORE).getValue();

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(now)))) {
            return false;
        }
        return TimeUtil.isBetween(store_open_time, store_close_time);
    }

    @ResponseBody
    @PostMapping("getDeliveryArriveTime")
    public Object getDeliveryArriveTime(@RequestParam(required = false) Long orderId) {
        Date deliveryDate;

        Optional<Order> order = Optional.ofNullable(orderService.getById(orderId));
        order.orElseThrow(() -> new SysException(OrderExceptionEnum.ORDER_NOT_EXIST));

        int base_express_time = Integer.parseInt(settingService.getSettingByName(order.get().getStoreId(),"base_express_time", SettingScope.EXPRESS).getValue());
        int average_express_time = Integer.parseInt(settingService.getSettingByName(order.get().getStoreId(),"average_express_time", SettingScope.EXPRESS).getValue());
        int deliverier_count = Integer.parseInt(settingService.getSettingByName(order.get().getStoreId(),"deliverier_count", SettingScope.EXPRESS).getValue());
        Date current = new Date();
        List<Order> orderList = orderService.getTodayOrderByStateU(null, OrderStateEx.WAIT_EAT).stream()
                .filter(item -> {
                    // 返回今天的要配送的订单
                    Date target = item.getAppointmentDate() == null ? item.getCreateDate() : item.getAppointmentDate();
                    return DateUtil.isSameDay(target, current);
                })
                .sorted((o1, o2) -> {
                    Date dateForO1 = o1.getAppointmentDate() == null ? o1.getCreateDate() : o1.getAppointmentDate();
                    Date dateForO2 = o2.getAppointmentDate() == null ? o2.getCreateDate() : o2.getAppointmentDate();
                    return dateForO1.compareTo(dateForO2);
                })
                .collect(Collectors.toList());

        int index = 0;
        if (order.isPresent()) {
            if (order.get().getAppointmentDate() != null) {
                return new DeliveryArriveTimeDto(order.get().getAppointmentDate());
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
