package net.novaborn.takeaway.coupon.util;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.coupon.entity.Coupon;
import net.novaborn.takeaway.goods.entity.Goods;

import java.util.*;
import java.util.stream.Collectors;

public class CouponUtil {

    /**
     * 获取优惠卷的限制金额
     *
     * @param coupon
     * @return
     */
    public static Map<String, List<String>> getCouponRule(Coupon coupon) {
        Map<String, List<String>> couponRules = new HashMap<>();
        if (!coupon.getAllowCategory().isBlank()) {
            couponRules.put("allowCategory", mapCategoryId(List.of(coupon.getAllowCategory().split(","))));
        } else {
            couponRules.put("allowCategory", new ArrayList());
        }

        if (!coupon.getAllowGoods().isBlank()) {
            couponRules.put("allowGoods", List.of(coupon.getAllowGoods().split(",")));
        } else {
            couponRules.put("allowGoods", new ArrayList());
        }

        if (!coupon.getLimitCategory().isBlank()) {
            couponRules.put("limitCategory", mapCategoryId(List.of(coupon.getLimitCategory().split(","))));
        } else {
            couponRules.put("limitCategory", new ArrayList());
        }

        if (!coupon.getLimitGoods().isBlank()) {
            couponRules.put("limitGoods", List.of(coupon.getLimitGoods().split(",")));
        } else {
            couponRules.put("limitGoods", new ArrayList());
        }

        return couponRules;
    }

    public static boolean isDiscount(Goods goods, Map<String, List<String>> couponRules) {
        boolean result = true;

        if (!couponRules.get("limitCategory").isEmpty()) {
            result = !couponRules.get("limitCategory").contains(goods.getCategoryId().toString());
            if (!result) {
                return result;
            }
        }

        if (!couponRules.get("limitGoods").isEmpty()) {
            result = !couponRules.get("limitGoods").contains(goods.getName());
            if (!result) {
                return false;
            }
        }

        if (!couponRules.get("allowCategory").isEmpty()) {
            result = couponRules.get("allowCategory").contains(goods.getCategoryId().toString());
            if (!result) {
                return false;
            }
        }

        if (!couponRules.get("allowGoods").isEmpty()) {
            result = couponRules.get("allowGoods").contains(goods.getName());
            if (!result) {
                return false;
            }
        }

        return result;
    }

    private static List<String> mapCategoryId(List<String> categoryList) {
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);
        return categoryList.parallelStream()
                .map(item -> {
                    Optional<Category> category = categoryService.getByName(item);
                    return category.map(value -> value.getId().toString()).orElse(null);
                })
                .collect(Collectors.toList());
    }
}
