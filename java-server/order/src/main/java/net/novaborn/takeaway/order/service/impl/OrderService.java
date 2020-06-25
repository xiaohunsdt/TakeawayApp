package net.novaborn.takeaway.order.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.enums.From;
import net.novaborn.takeaway.common.utils.FromFormatUtil;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Service
public class OrderService extends ServiceImpl<IOrderDao, Order> implements IOrderService {
    private OrderItemService orderItemService;

    private GoodsService goodsService;

    private GoodsStockService goodsStockService;

    private CouponService couponService;

    @Override
    public Optional<Order> getById(String orderId, boolean isShowDeleted) {
        return this.baseMapper.getById(orderId, isShowDeleted);
    }

    @Override
    public List<Order> getOrderListByUserId(String userId) {
        return this.getOrderListByUserId(userId, null, null, false);
    }

    @Override
    public List<Order> getOrderListByUserId(String userId, PaymentWay paymentWay, OrderState orderState, boolean isShowDeleted) {
        return this.baseMapper.getOrderListByUserId(userId, paymentWay, orderState, isShowDeleted);
    }

    @Override
    public List<Order> getOrderList(Map args) {
        return this.baseMapper.getOrderList(args);
    }

    @Override
    public IPage<Order> getOrderListByPage(Page page, Map args) {
        return this.baseMapper.getOrderListByPage(page, args);
    }


    @Override
    public int getWaitingReceiveOrderCount(DeliveryType deliveryType) {
        return this.baseMapper.getWaitingReceiveOrderCount(deliveryType);
    }

    @Override
    public IPage<Order> getOrderListByPageU(Page page, String userId, OrderStateEx orderState) {
        return this.baseMapper.getOrderListByPageU(page, userId, orderState);
    }

    @Override
    public int getOrderCountByStateU(String userId, OrderStateEx orderState) {
        return this.baseMapper.getOrderCountByStateU(userId, orderState);
    }

    @Override
    public List<Order> getTodayOrderByStateU(String userId, OrderStateEx orderState) {
        return this.baseMapper.getTodayOrderByStateU(userId, orderState);
    }

    @Override
    public int getTodayOrderCountByStateU(String userId, OrderStateEx orderState) {
        return this.baseMapper.getTodayOrderCountByStateU(userId, orderState);
    }

    @Override
    public int getOrderCount(Date day, DeliveryType deliveryType) {
        return this.baseMapper.getOrderCount(day, deliveryType);
    }

    @Override
    public void setDiscount(Order order, List<OrderItem> orderItemList, String couponId) {
        couponService.getDiscountMoney(order, orderItemList, couponId);
    }

    @Override
    public void setDiscount(Order order, List<OrderItem> orderItemList, int discount) {
        // 刷卡除外
        if (order.getPaymentWay() == PaymentWay.CREDIT_CARD) {
            return;
        }

        int realPrice = orderItemList.parallelStream()
                .filter(orderItem -> !StrUtil.isBlank(orderItem.getGoodsId()))
                .map(orderItem -> {
                    Goods goods = goodsService.getById(orderItem.getGoodsId());
                    // 鸭货除外
                    if (goods.getCategoryId().equals("b6db18e5f06d02f119411d0ca4776df2")) {
                        return orderItem.getGoodsPrice() * orderItem.getGoodsCount();
                    } else {
                        return orderItem.getGoodsPrice() * orderItem.getGoodsCount() * discount / 100;
                    }
                })
                .reduce(0, (x, y) -> x + y);

        order.setDiscount((short) discount);
        order.setDiscountedPrices(order.getAllPrice() - realPrice);
        order.setRealPrice(realPrice);
    }

    @Override
    public void checkOrder(Order order, List<OrderItem> orderItemList, String couponId) {
        int allCount = orderItemList.parallelStream().mapToInt(OrderItem::getGoodsCount).sum();
        int allPrice = orderItemList.parallelStream().mapToInt(item -> item.getGoodsPrice() * item.getGoodsCount()).sum();

        order.setGoodsCount(allCount);
        order.setAllPrice(allPrice);
        order.setRealPrice(allPrice);

        //设置 优惠卷折扣
        if (!StrUtil.isBlank(couponId)) {
            this.setDiscount(order, orderItemList, couponId);
        }

        // 设置互联优惠
//        if (order.getPaymentWay() != PaymentWay.CREDIT_CARD) {
//            if (order.getRealPrice() >= 22000) {
//                giveGift(order, orderItemList);
//            } else if (order.getRealPrice() >= 15000) {
//                if (order.getFrom() != null && (order.getFrom() == From.YONSEI || order.getFrom() == From.EWHA || order.getFrom() == From.HONGIK || order.getFrom() == From.SOGANG)) {
//                    giveGift(order, orderItemList);
//                }
//            }
//        }


        // 设置互联折扣
//        if (order.getFrom() != null) {
//            // 延世大学联活动
//            if (order.getFrom() == From.YONSEI) {
//                if (orderDto.getCouponId() != null && !orderDto.getCouponId().isBlank()) {
//                    throw new SysException(OrderExceptionEnum.ORDER_CAN_NOT_BE_DISCOUNTED_BECAUSE_COUPON);
//                }
//                orderService.setDiscount(order, orderDto.getOrderItems(), 88);
//            }
//        }

        //填写订单信息
        int number;
        if (order.getAppointmentDate() == null) {
            // 一般订单
            number = this.getOrderCount(new Date(), DeliveryType.NORMAL) + 1;
        } else {
            // 预约订单
            number = 500000 + DateUtil.dayOfMonth(order.getAppointmentDate()) * 1000 + this.getOrderCount(order.getAppointmentDate(), DeliveryType.APPOINTMENT) + 1;
        }
        order.setNumber(number);

        //设置订单的支付状态
        if (order.getPaymentWay() == PaymentWay.CREDIT_CARD || order.getPaymentWay() == PaymentWay.CASH) {
            //刷卡和现金支付设置为后付状态
            order.setPayState(PayState.PAY_LATER);
        } else {
            order.setPayState(PayState.UN_PAY);
        }

        // 处理订单不需要支付的情况
        if (order.getRealPrice() <= 0) {
            order.setPaymentWay(PaymentWay.CASH);
            order.setPayState(PayState.PAID);
        }
    }

    @Override
    public List<Map.Entry<String, Integer>> getGoodsSales(List<Order> orderList) {
        TreeMap<String, Integer> goodsSale = new TreeMap<>();

        orderList.stream()
                .filter(order -> order.getPayState() != PayState.UN_PAY && order.getOrderState() != OrderState.REFUND)
                .forEach(order -> {
                    orderItemService.selectByOrderId(order.getId()).forEach(orderItem -> {
                        Integer count = orderItem.getGoodsCount();
                        if (goodsSale.containsKey(orderItem.getGoodsName())) {
                            count += goodsSale.get(orderItem.getGoodsName());
                        }
                        goodsSale.put(orderItem.getGoodsName(), count);
                    });
                });

        List<Map.Entry<String, Integer>> list = new ArrayList<>(goodsSale.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }
}
