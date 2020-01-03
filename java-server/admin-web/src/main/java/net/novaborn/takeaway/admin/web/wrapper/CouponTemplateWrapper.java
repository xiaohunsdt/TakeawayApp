package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.util.StrUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class CouponTemplateWrapper extends BaseControllerWrapper {

    public CouponTemplateWrapper(Object element) {
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
    }
}
