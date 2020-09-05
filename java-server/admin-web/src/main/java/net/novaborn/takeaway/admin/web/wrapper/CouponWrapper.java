package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.util.StrUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;

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
        UserService userService = SpringContextHolder.getBean(UserService.class);
        User user = userService.getById((Long) map.get("userId"));

        if (user != null) {
            map.put("nickName", user.getNickName());
        }

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
