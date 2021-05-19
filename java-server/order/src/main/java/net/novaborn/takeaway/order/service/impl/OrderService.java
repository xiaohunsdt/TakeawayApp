package net.novaborn.takeaway.order.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.CommonUtil;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.enums.CouponState;
import net.novaborn.takeaway.coupon.services.impl.CouponLogService;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.order.dao.IOrderDao;
import net.novaborn.takeaway.order.dto.OrderDto;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderDetail;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.enums.*;
import net.novaborn.takeaway.order.exception.OrderExceptionEnum;
import net.novaborn.takeaway.order.exception.OrderServiceException;
import net.novaborn.takeaway.order.service.IOrderService;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Setter
    protected Map<String, Goods> gifts;
    private OrderItemService orderItemService;

    private OrderDetailService orderDetailService;

    private GoodsService goodsService;

    private ProduceService produceService;

    private GoodsStockService goodsStockService;

    private CouponService couponService;

    private CouponLogService couponLogService;

    private SettingService settingService;

    @PostConstruct
    public void init() {
        gifts = new HashMap<>();
        gifts.put("川香卤蛋", goodsService.getFirstByProduceId(1301894882715054082L));
        gifts.put("饮料", goodsService.getFirstByProduceId(1301894885395214337L));
/*        gifts.put("口水鸡", goodsService.getFirstByProduceId(1301894895893557250L));
        gifts.put("毛血旺", goodsService.getFirstByProduceId(1301894888289284097L));
        gifts.put("水煮毛肚", goodsService.getFirstByProduceId(1341387293693435906L));
        gifts.put("水煮鱼", goodsService.getFirstByProduceId(1301894884791234561L));
        gifts.put("川香烤鱼", goodsService.getFirstByProduceId(1301894885168721921L));
        gifts.put("双椒烤鱼", goodsService.getFirstByProduceId(1332216962323963905L));*/
        gifts.put("鸭翅", goodsService.getFirstByProduceId(1301894884405358594L));
        gifts.put("鸭锁骨", goodsService.getFirstByProduceId(1301894884560547841L));
        gifts.put("鸭脖", goodsService.getFirstByProduceId(1301894883776212994L));
        gifts.put("猪蹄(2份)", goodsService.getFirstByProduceId(1301894884166283266L));
    }

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
    public int getOrderCount(Date day, OrderType orderType) {
        return this.baseMapper.getOrderCount(null, day, orderType);
    }

    @Override
    public int getOrderCount(Long storeId, Date day, OrderType orderType) {
        return this.baseMapper.getOrderCount(storeId, day, orderType);
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
//                return orderItem.getGoodsPrice() * orderItem.getGoodsCount() * discount / 100;

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
        int deliveryPrice = settingService.getSettingByName(order.getStoreId(), "delivery_price", SettingScope.DELIVERY).getValueAsInt();

        order.setAllPrice(allPrice);
        order.setRealPrice(allPrice);
        order.setGoodsCount(allCount);

        if (order.getOrderType() == OrderType.NORMAL || order.getOrderType() == OrderType.APPOINTMENT) {
            order.setAllPrice(order.getAllPrice() + deliveryPrice);
            order.setRealPrice(order.getRealPrice() + deliveryPrice);
            order.setDeliveryPrice(deliveryPrice);
        }
    }

    private int getRealPriceWithoutSet(List<OrderItem> orderItemList) {
        return orderItemList.stream().filter(orderItem -> {
            Produce produce = produceService.getById(orderItem.getProduceId());
            return produce.getCategoryId() != 1301894880592736258L;
        }).mapToInt(orderItem -> orderItem.getGoodsPrice() * orderItem.getGoodsCount()).sum();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderDto orderDto) {
        Order order = orderDto.getOrder();

        OrderDetail orderDetail = orderDto.getOrderDetail();
        List<OrderItem> orderItems = orderDto.getOrderItems();

        //订单为自取订单的情况检测
        if (order.getOrderType() == OrderType.SELF) {
            if (orderDetail.getAppointmentDate() != null) {
                if (orderDetail.getPhone() == null || StrUtil.isBlank(orderDetail.getPhone())) {
                    throw new SysException(OrderExceptionEnum.ORDER_SELF_HAVE_NO_PHONE);
                }

                if (!CommonUtil.validateKoreaPhone(orderDetail.getPhone())) {
                    throw new SysException(OrderExceptionEnum.PHONE_FORMAT_ERROR);
                }
            }
        }

        //检测订单商品项是否可以下单
        orderItemService.checkOrderItems(order.getOrderType(), orderItems);
        this.checkOrder(order, orderItems);
        this.postCheckOrder(orderDto);

        //先生成订单，再生成订单产品详情
        if (order.insert()) {
            // 生成订单详情
            orderDetail.setOrderId(order.getId());
            orderDetailService.save(orderDetail);

            //生成订购项
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
                couponService.updateById(coupon);

                // 添加优惠卷使用记录
                couponLogService.makeNewCouponLog(order, coupon);
            }
        }

        return order;
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

    private void postCheckOrder(OrderDto orderDto) {
        // 设置优惠
//        if (orderDto.getOrder().getStoreId() == 1302193963869949953L && orderDto.getOrder().getPaymentWay() != PaymentWay.CREDIT_CARD) {
//            int realPrice = getRealPriceWithoutSet(orderDto.getOrderItems());

//            Goods gift = null;
//            String giftName = null;
//            if (orderDto.getOrder().getRealPrice() >= 50000) {
//                if (goodsStockService.checkStock(gifts.get("猪蹄(2份)"), 1)) {
//                    gift = gifts.get("猪蹄(2份)");
//                    giftName = "猪蹄(2份)";
//                }
//            } else if (orderDto.getOrder().getRealPrice() >= 40000) {
//                if (goodsStockService.checkStock(gifts.get("鸭脖"), 1)) {
//                    gift = gifts.get("鸭脖");
//                    giftName = "鸭脖";
//                }
//            } else if (orderDto.getOrder().getRealPrice() >= 30000) {
//                if (goodsStockService.checkStock(gifts.get("鸭锁骨"), 1)) {
//                    gift = gifts.get("鸭锁骨");
//                    giftName = "鸭锁骨";
//                }
//            } else if (orderDto.getOrder().getRealPrice() >= 25000) {
//                if (goodsStockService.checkStock(gifts.get("鸭翅"), 1)) {
//                    gift = gifts.get("鸭翅");
//                    giftName = "鸭翅";
//                }
//            } else if (realPrice >= 20000) {
//                if (goodsStockService.checkStock(gifts.get("饮料"), 1)) {
//                    gift = gifts.get("饮料");
//                    giftName = "饮料";
//                }
//            } else if (realPrice >= 15000) {
//                if (goodsStockService.checkStock(gifts.get("川香卤蛋"), 1)) {
//                    gift = gifts.get("川香卤蛋");
//                    giftName = "川香卤蛋";
//                }
//            }

        //            if (realPrice >= 80000) {
//                if (goodsStockService.checkStock(gifts.get("双椒烤鱼"), 1)) {
//                    gift = gifts.get("双椒烤鱼");
//                    giftName = "双椒烤鱼";
//                }
//            } else if (realPrice >= 70000) {
//                if (goodsStockService.checkStock(gifts.get("川香烤鱼"), 1)) {
//                    gift = gifts.get("川香烤鱼");
//                    giftName = "川香烤鱼";
//                }
//            } else if (realPrice >= 60000) {
//                if (goodsStockService.checkStock(gifts.get("水煮鱼"), 1)) {
//                    gift = gifts.get("水煮鱼");
//                    giftName = "水煮鱼";
//                }
//            } else if (realPrice>= 50000) {
//                if (goodsStockService.checkStock(gifts.get("水煮毛肚"), 1)) {
//                    gift = gifts.get("水煮毛肚");
//                    giftName = "水煮毛肚";
//                }
//            } else if (realPrice >= 40000) {
//                if (goodsStockService.checkStock(gifts.get("毛血旺"), 1)) {
//                    gift = gifts.get("毛血旺");
//                    giftName = "毛血旺";
//                }
//            } else if (realPrice >= 30000) {
//                if (goodsStockService.checkStock(gifts.get("口水鸡"), 1)) {
//                    gift = gifts.get("口水鸡");
//                    giftName = "口水鸡";
//                }
//            }

//            if (gift != null) {
//                OrderItem orderItem = new OrderItem();
//                orderItem.setGoodsId(gift.getId());
//                orderItem.setProduceId(gift.getProduceId());
//                orderItem.setProduceName("满赠福利");
//                orderItem.setGoodsTitle(giftName);
//                orderItem.setGoodsThumb(gift.getThumb());
//                orderItem.setGoodsPrice(0);
//                orderItem.setGoodsCount(1);
//                orderDto.getOrderItems().add(orderItem);
//                orderDto.getOrder().setGoodsCount(orderDto.getOrder().getGoodsCount() + 1);
//            }
//        }


        //设置 活动折扣
        this.setDiscount(orderDto.getOrder(),orderDto.getOrderItems(),88);

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
                number = this.getOrderCount(orderDto.getOrder().getStoreId(), currentDate, OrderType.NORMAL) + 1;
                break;
            case APPOINTMENT:
                // 预约订单
                number = 500000 + DateUtil.dayOfMonth(orderDto.getOrderDetail().getAppointmentDate()) * 1000 + this.getOrderCount(orderDto.getOrder().getStoreId(), orderDto.getOrderDetail().getAppointmentDate(), OrderType.APPOINTMENT) + 1;
                break;
            case IN_STORE:
                // 堂食订单
                number = 600000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getOrderCount(orderDto.getOrder().getStoreId(), currentDate, OrderType.IN_STORE) + 1;
                break;
            case EXPRESS:
                // 快递订单
                number = 700000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getOrderCount(orderDto.getOrder().getStoreId(), currentDate, OrderType.EXPRESS) + 1;
                break;
            case SELF:
                // 自取订单
                number = 800000 + DateUtil.dayOfMonth(currentDate) * 1000 + this.getOrderCount(orderDto.getOrder().getStoreId(), currentDate, OrderType.SELF) + 1;
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
}
