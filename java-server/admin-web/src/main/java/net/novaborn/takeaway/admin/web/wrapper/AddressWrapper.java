package net.novaborn.takeaway.admin.web.wrapper;

import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class AddressWrapper extends BaseControllerWrapper {

    public AddressWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        map.put("user", userService.getById((String) map.get("userId")));
        map.remove("userId");
    }
}
