package net.novaborn.takeaway.user.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
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

    private User user;

    public OrderWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        this.user = userService.getById((String) map.get("userId"));

        Date createDate = (Date) map.get("createDate");

        map.put("user", user);
        map.put("createDate", DateUtil.format(createDate, "MM-dd HH:mm"));
        map.remove("userId");
    }
}
