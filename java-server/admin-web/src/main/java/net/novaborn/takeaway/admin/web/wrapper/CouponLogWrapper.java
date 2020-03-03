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
public class CouponLogWrapper extends BaseControllerWrapper {

    public CouponLogWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        User user = userService.getById((String) map.get("userId"));

        map.put("nickName", user.getNickName());
    }
}
