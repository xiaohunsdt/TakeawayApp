package net.novaborn.takeaway.order.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.enums.From;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.service.IOrderService;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

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

    private SettingService settingService;

    @Setter
    protected List<Goods> gifts;

    @PostConstruct
    public void init() {
        gifts = new ArrayList<>();
        gifts.add(goodsService.getById(1308791764220502017L));
        gifts.add(goodsService.getById(1308791764220502017L));
        gifts.add(goodsService.getById(1308791764220502017L));
        gifts.add(goodsService.getById(1308791979291828226L));
    }

    @Override
    public Optional<Order> getById(Long orderId, boolean isShowDeleted) {
        return this.baseMapper.getById(orderId, isShowDeleted);
    }

    @Override
    public List<Order> getOrderListByUserId(Long userId) {
        return this.getOrderListByUserId(userId, null, null, false);
    }

    @Override
    public List<Order> getOrderListByUserId(Long userId, PaymentWay paymentWay, OrderState orderState, boolean isShowDeleted) {
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
    public IPage<Order> getOrderListByPageU(Page page, Long userId, OrderStateEx orderState) {
        return this.baseMapper.getOrderListByPageU(page, userId, orderState);
    }

    @Override
    public int getOrderCountByStateU(Long userId, OrderStateEx orderState) {
        return this.baseMapper.getOrderCountByStateU(userId, orderState);
    }

    @Override
    public List<Order> getTodayOrderByStateU(Long userId, OrderStateEx orderState) {
        return this.baseMapper.getTodayOrderByStateU(userId, orderState);
    }

    @Override
    public int getTodayOrderCountByStateU(Long userId, OrderStateEx orderState) {
        return this.baseMapper.getTodayOrderCountByStateU(userId, orderState);
    }

    @Override
    public int getOrderCount(Date day, DeliveryType deliveryType) {
        return this.baseMapper.getOrderCount(day, deliveryType);
    }

    @Override
    public void setDiscount(Order order, List<OrderItem> orderItemList, Long couponId) {
        couponService.getDiscountMoney(order, orderItemList, couponId);
    }

    @Override
    public void setDiscount(Order order, List<OrderItem> orderItemList, int discount) {
        // 刷卡除外
        if (order.getPaymentWay() == PaymentWay.CREDIT_CARD) {
            return;
        }

        int realPrice = orderItemList.parallelStream()
                .filter(orderItem -> orderItem.getGoodsId() != null)
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
    public void checkOrder(Order order, List<OrderItem> orderItemList) {
        int allCount = orderItemList.parallelStream().mapToInt(OrderItem::getGoodsCount).sum();
        int allPrice = orderItemList.parallelStream().mapToInt(item -> item.getGoodsPrice() * item.getGoodsCount()).sum();
        int deliveryPrice = settingService.getSettingByName(order.getStoreId(), "delivery_price", SettingScope.EXPRESS).getValueAsInt();

        order.setGoodsCount(allCount);
        order.setAllPrice(allPrice + deliveryPrice);
        order.setRealPrice(allPrice + deliveryPrice);
        order.setDeliveryPrice(deliveryPrice);
    }

    @Override
    public void postCheckOrder(Order order, List<OrderItem> orderItemList, Long couponId) {
        // 设置优惠
        if (order.getPaymentWay() != PaymentWay.CREDIT_CARD) {
            Goods gift = null;
            int randomInt = RandomUtil.randomInt(gifts.size());
//            if (order.getRealPrice() >= 18000) {
//                if (goodsStockService.checkStock(gifts.get(randomInt), 1)) {
//                    gift = gifts.get(randomInt);
//                }
//            } else if (order.getRealPrice() >= 12000 && (From.YONSEI.equals(order.getFrom()) || From.SOGANG.equals(order.getFrom()))) {
//                if (goodsStockService.checkStock(gifts.get(randomInt), 1)) {
//                    gift = gifts.get(randomInt);
//                }
//            }
            if (goodsStockService.checkStock(gifts.get(randomInt), 1)) {
                gift = gifts.get(randomInt);
            }
            if (gift != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setGoodsId(gift.getId());
                orderItem.setGoodsName("中秋福利-" + gift.getName());
                orderItem.setGoodsThumb(gift.getThumb());
                orderItem.setGoodsPrice(0);
                orderItem.setGoodsCount(1);
                orderItemList.add(orderItem);
                order.setGoodsCount(order.getGoodsCount() + 1);
            }
        }
//        if (order.getPaymentWay() != PaymentWay.CREDIT_CARD) {
//            Goods gift = null;
//            if (order.getRealPrice() >= 40000) {
//                if (goodsStockService.checkStock(gifts.get("鸭脖"), 1)) {
//                    gift = gifts.get("鸭脖");
//                }
//            } else if (order.getRealPrice() >= 30000) {
//                if (goodsStockService.checkStock(gifts.get("鸭锁骨"), 1)) {
//                    gift = gifts.get("鸭锁骨");
//                }
//            } else if (order.getRealPrice() >= 25000) {
//                if (goodsStockService.checkStock(gifts.get("鸭翅"), 1)) {
//                    gift = gifts.get("鸭翅");
//                }
//            } else if (order.getRealPrice() >= 20000) {
//                if (goodsStockService.checkStock(gifts.get("饮料"), 1)) {
//                    gift = gifts.get("饮料");
//                }
//            } else if (order.getRealPrice() >= 15000) {
//                if (goodsStockService.checkStock(gifts.get("川香卤蛋"), 1)) {
//                    gift = gifts.get("川香卤蛋");
//                }
//            }
//        }

        //设置 优惠卷折扣
        if (couponId != null) {
            this.setDiscount(order, orderItemList, couponId);
        }

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
