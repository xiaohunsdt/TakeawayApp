package net.novaborn.takeaway.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.coupon.service.impl.CouponService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.OrderState;
import net.novaborn.takeaway.order.enums.OrderStateEx;
import net.novaborn.takeaway.order.enums.PaymentWay;
import net.novaborn.takeaway.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private GoodsService goodsService;

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
    public IPage<Order> getOrderListByPage(Page page, Map args) {
        return this.baseMapper.getOrderListByPage(page, args);
    }


    @Override
    public int getWaitingReceiveOrderCount() {
        return this.baseMapper.getWaitingReceiveOrderCount();
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
    public int getOrderCountToday() {
        return this.baseMapper.getOrderCountToday();
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
        int AllPrice = orderItemList.parallelStream().mapToInt(item -> item.getGoodsPrice() * item.getGoodsCount()).sum();

        order.setGoodsCount(allCount);
        order.setAllPrice(AllPrice);
    }
}
