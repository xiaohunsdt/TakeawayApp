package net.novaborn.takeaway.user.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Date;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class OrderWrapper extends BaseControllerWrapper {
    public OrderWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        UserService userService = SpringContextHolder.getBean(UserService.class);

        User user = userService.getById((Long) map.get("userId"));

        map.put("user", user);
        map.put("createDate", DateUtil.format((Date) map.get("createDate"), "MM-dd HH:mm"));
        map.remove("userId");
    }
}
