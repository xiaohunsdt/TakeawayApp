package net.novaborn.takeaway.admin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.impl.ActivityService;
import net.novaborn.takeaway.banner.entity.Banner;
import net.novaborn.takeaway.banner.service.impl.BannerService;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.coupon.entity.CouponLog;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.services.impl.CouponLogService;
import net.novaborn.takeaway.coupon.services.impl.CouponService;
import net.novaborn.takeaway.coupon.services.impl.CouponTemplateService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.order.entity.Comment;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.entity.OrderItem;
import net.novaborn.takeaway.order.service.impl.CommentService;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminApplicationTest {
    @Autowired
    ActivityService activityService;

    @Autowired
    BannerService bannerService;

    @Autowired
    SettingService settingService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsStockService goodsStockService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    CouponService couponService;

    @Autowired
    CouponTemplateService couponTemplateService;

    @Autowired
    CouponLogService couponLogService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    CommentService commentService;

    @Test
    void test() {
        System.out.println(DateUtil.date().getField(DateField.HOUR_OF_DAY));
    }

    @Test
    void test2() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();
        System.out.println(defaultIdentifierGenerator.nextId(new Object()));
    }

    @Test
    void test3() {
        DefaultIdentifierGenerator defaultIdentifierGenerator = new DefaultIdentifierGenerator();
        Map<String, String> userMap = new HashMap();
        Map<String, String> addressMap = new HashMap();
        Map<String, String> categoryMap = new HashMap();
        Map<String, String> goodMap = new HashMap();
        Map<String, String> orderMap = new HashMap();
        Map<String, String> couponTemplateMap = new HashMap();
        Map<String, String> couponMap = new HashMap();

//        activityService.list().stream().sorted(Comparator.comparing(Activity::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//            Activity activity = BeanUtil.copyProperties(item, Activity.class);
//            activity.setId(defaultIdentifierGenerator.nextId(item).toString());
//            activity.insertOrUpdate();
//        });
//
//        bannerService.list().stream().sorted(Comparator.comparing(Banner::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Banner banner = BeanUtil.copyProperties(item, Banner.class);
//            banner.setId(defaultIdentifierGenerator.nextId(item).toString());
//            banner.insertOrUpdate();
//        });
//
//        settingService.list().forEach(item -> {
//            item.deleteById();
//
//            Setting setting = BeanUtil.copyProperties(item, Setting.class);
//            setting.setId(defaultIdentifierGenerator.nextId(item).toString());
//            setting.insertOrUpdate();
//        });
//
//        userService.list().stream().sorted(Comparator.comparing(User::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            User temp = BeanUtil.copyProperties(item, User.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.insertOrUpdate();
//
//            userMap.put(item.getId(), temp.getId());
//        });
//
//        categoryService.list().stream().sorted(Comparator.comparing(Category::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Category temp = BeanUtil.copyProperties(item, Category.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.insertOrUpdate();
//
//            categoryMap.put(item.getId(), temp.getId());
//        });
//
//        goodsService.list().stream().sorted(Comparator.comparing(Goods::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Goods temp = BeanUtil.copyProperties(item, Goods.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setCategoryId(categoryMap.get(item.getCategoryId()));
//            temp.insertOrUpdate();
//
//            goodMap.put(item.getId(), temp.getId());
//        });
//
//        goodsStockService.list().forEach(item -> {
//            item.deleteById();
//
//            GoodsStock temp = BeanUtil.copyProperties(item, GoodsStock.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setGoodsId(goodMap.get(item.getGoodsId()));
//            temp.insertOrUpdate();
//        });
//
//        addressService.list().stream().sorted(Comparator.comparing(Address::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Address temp = BeanUtil.copyProperties(item, Address.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setUserId(userMap.get(item.getUserId()));
//            temp.insertOrUpdate();
//
//            addressMap.put(item.getId(), temp.getId());
//        });
//
//        orderService.list().stream().sorted(Comparator.comparing(Order::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Order temp = BeanUtil.copyProperties(item, Order.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setUserId(userMap.get(item.getUserId()));
//            temp.setAddressId(addressMap.get(item.getAddressId()));
//            temp.insertOrUpdate();
//
//            orderMap.put(item.getId(), temp.getId());
//        });
//
//        orderItemService.list().forEach(item -> {
//            item.deleteById();
//
//            try {
//                OrderItem temp = BeanUtil.copyProperties(item, OrderItem.class);
//                temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//                temp.setGoodsId(goodMap.get(item.getGoodsId()));
//                temp.setOrderId(orderMap.get(item.getOrderId()));
//                temp.insertOrUpdate();
//            } catch (Exception e) {
//                System.out.println(item);
//            }
//        });
//
//        commentService.list().stream().sorted(Comparator.comparing(Comment::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            try {
//                Comment temp = BeanUtil.copyProperties(item, Comment.class);
//                temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//                temp.setUserId(userMap.get(item.getUserId()));
//                temp.setOrderId(orderMap.get(item.getOrderId()));
//                temp.insertOrUpdate();
//            } catch (Exception e) {
//                System.out.println(item);
//            }
//        });
//
//        couponTemplateService.list().stream().sorted(Comparator.comparing(CouponTemplate::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            CouponTemplate temp = BeanUtil.copyProperties(item, CouponTemplate.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.insertOrUpdate();
//
//            couponTemplateMap.put(item.getId(), temp.getId());
//        });
//
//        couponService.list().stream().sorted(Comparator.comparing(Coupon::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            Coupon temp = BeanUtil.copyProperties(item, Coupon.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setUserId(userMap.get(item.getUserId()));
//            temp.insertOrUpdate();
//
//            couponMap.put(item.getId(), temp.getId());
//        });
//
//        couponLogService.list().stream().sorted(Comparator.comparing(CouponLog::getCreateDate).reversed()).forEach(item -> {
//            item.deleteById();
//
//            CouponLog temp = BeanUtil.copyProperties(item, CouponLog.class);
//            temp.setId(defaultIdentifierGenerator.nextId(item).toString());
//            temp.setUserId(userMap.get(item.getUserId()));
//            temp.setCouponId(couponMap.get(item.getCouponId()));
//            temp.setOrderId(orderMap.get(item.getOrderId()));
//            temp.insertOrUpdate();
//        });
    }
}