package net.novaborn.takeaway.user.web.wrapper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;

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
    }
}
