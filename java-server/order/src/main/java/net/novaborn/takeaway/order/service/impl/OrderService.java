package net.novaborn.takeaway.order.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.dto.OrderDto;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.exception.OrderServiceException;
import net.novaborn.takeaway.order.service.IOrderService;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private ProduceService produceService;

    private GoodsStockService goodsStockService;

    private CouponService couponService;

    private SettingService settingService;

//    @Setter
//    protected List<Goods> gifts;

//    @PostConstruct
//    public void init() {
//        gifts = new ArrayList<>();
//        gifts.add(goodsService.getById(1308791764220502017L));
//    }

    @Override
    public boolean updateById(Order entity) {
        if (!super.updateById(entity)) {
            throw new OrderServiceException(OrderExceptionEnum.UPDATE_FAILED);
        }

        return true;
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
    public int getWaitingReceiveOrderCount(OrderType orderType) {
        return this.baseMapper.getWaitingReceiveOrderCount(orderType);
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
    public int getTodayOrderCount(Date day, OrderType orderType) {
        return this.baseMapper.getTodayOrderCount(day, orderType);
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
                Produce produce = produceService.getById(orderItem.getProduceId());
                // 鸭货除外
                if (produce.getCategoryId().equals(1301894880743731201L)) {
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
        int deliveryPrice = settingService.getSettingByName("delivery_price", SettingScope.DELIVERY).getValueAsInt();

        order.setAllPrice(allPrice);
        order.setRealPrice(allPrice);
        order.setGoodsCount(allCount);

        if (order.getOrderType() == OrderType.NORMAL || order.getOrderType() == OrderType.APPOINTMENT) {
            order.setAllPrice(order.getAllPrice() + deliveryPrice);
            order.setRealPrice(order.getRealPrice() + deliveryPrice);
            order.setDeliveryPrice(deliveryPrice);
        }
    }

    @Override
    public void postCheckOrder(OrderDto orderDto) {
        // 设置优惠
//        if (order.getPaymentWay() != PaymentWay.CREDIT_CARD) {
//            Goods gift = null;
//            if (order.getRealPrice() >= 18000) {
//                if (goodsStockService.checkStock(gifts.get(randomInt), 1)) {
//                    gift = gifts.get(randomInt);
//                }
//            } else if (order.getRealPrice() >= 12000 && (From.YONSEI.equals(order.getFrom()) || From.SOGANG.equals(order.getFrom()))) {
//                if (goodsStockService.checkStock(gifts.get(randomInt), 1)) {
//                    gift = gifts.get(randomInt);
//                }
//            }
//            if (gift != null) {
//                OrderItem orderItem = new OrderItem();
//                orderItem.setGoodsId(gift.getId());
//                orderItem.setGoodsName("中秋福利-" + gift.getName());
//                orderItem.setGoodsThumb(gift.getThumb());
//                orderItem.setGoodsPrice(0);
//                orderItem.setGoodsCount(1);
//                orderItemList.add(orderItem);
//                order.setGoodsCount(order.getGoodsCount() + 1);
//            }
//        }
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
        if (orderDto.getCouponId() != null) {
            this.setDiscount(orderDto.getOrder(), orderDto.getOrderItems(), orderDto.getCouponId());
        }

        //填写订单信息
        int number;
        Date currentDate = new Date();
        switch (orderDto.getOrder().getOrderType()) {
            case NORMAL:
                // 一般订单
                number = this.getTodayOrderCount(currentDate, OrderType.NORMAL) + 1;
                break;
            case APPOINTMENT:
                // 预约订单
                number = 500000 + DateUtil.dayOfMonth(orderDto.getOrderDetail().getAppointmentDate()) * 1000 + this.getTodayOrderCount(orderDto.getOrderDetail().getAppointmentDate(), OrderType.APPOINTMENT) + 1;
                break;
            case IN_STORE:
                // 堂食订单
                number = 600000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getTodayOrderCount(currentDate, OrderType.IN_STORE) + 1;
                break;
            case EXPRESS:
                // 快递订单
                number = 700000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getTodayOrderCount(currentDate, OrderType.EXPRESS) + 1;
                break;
            case SELF:
                // 自取订单
                number = 800000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getTodayOrderCount(currentDate, OrderType.SELF) + 1;
                break;
            default:
                number = 0;
        }
        orderDto.getOrder().setNumber(number);

        //设置订单的支付状态
        if (orderDto.getOrder().getPaymentWay() == PaymentWay.CREDIT_CARD || orderDto.getOrder().getPaymentWay() == PaymentWay.CASH) {
            //刷卡和现金支付设置为后付状态
            orderDto.getOrder().setPayState(PayState.PAY_LATER);
        } else {
            orderDto.getOrder().setPayState(PayState.UN_PAY);
        }

        // 处理订单不需要支付的情况
        if (orderDto.getOrder().getRealPrice() <= 0) {
            orderDto.getOrder().setPaymentWay(PaymentWay.CASH);
            orderDto.getOrder().setPayState(PayState.PAID);
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
                    if (goodsSale.containsKey(orderItem.getProduceName())) {
                        count += goodsSale.get(orderItem.getProduceName());
                    }
                    goodsSale.put(orderItem.getProduceName(), count);
                });
            });

        List<Map.Entry<String, Integer>> list = new ArrayList<>(goodsSale.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }
}
