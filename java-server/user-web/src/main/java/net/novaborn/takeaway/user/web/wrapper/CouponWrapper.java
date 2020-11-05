package net.novaborn.takeaway.user.web.wrapper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;

import java.util.Date;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class CouponWrapper extends BaseControllerWrapper {

    public CouponWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        StoreService storeService = SpringContextHolder.getBean(StoreService.class);

        if (StrUtil.isNotBlank((String) map.get("allowCategory"))) {
            map.put("allowCategory", ((String) map.get("allowCategory")).split(","));
        }

        if (StrUtil.isNotBlank((String) map.get("limitCategory"))) {
            map.put("limitCategory", ((String) map.get("limitCategory")).split(","));
        }

        if (StrUtil.isNotBlank((String) map.get("allowGoods"))) {
            map.put("allowGoods", ((String) map.get("allowGoods")).split(","));
        }

        if (StrUtil.isNotBlank((String) map.get("limitGoods"))) {
            map.put("limitGoods", ((String) map.get("limitGoods")).split(","));
        }

        if (map.get("expireDate") != null) {
            map.put("expireDate", DateUtil.format((Date) map.get("expireDate"), "yyyy-MM-dd HH:mm"));
        }

        if ((Long) map.get("storeId") == 0L) {
            map.put("belong", "");
        } else {
            Store store = storeService.getById((Long) map.get("storeId"));
            map.put("belong", store.getName());
        }
    }
}
