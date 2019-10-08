package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
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
        Date createDate = (Date) map.get("createDate");
        map.put("user", userService.getById((String) map.get("userId")));
        map.put("createDate", DateUtil.format(createDate, "MM-dd HH:mm"));
        map.remove("userId");
    }
}
