package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Date;
import java.util.Map;

/**
 * 商品包装类
 *
 * @author xiaohun
 */
public class RefundLogWrapper extends BaseControllerWrapper {

    public RefundLogWrapper(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
//        AdminService adminService = SpringContextHolder.getBean(AdminService.class);
//        Admin admin = adminService.getById((Long) map.get("adminId"));
//        map.put("adminName", admin.getUserName());

        UserService userService = SpringContextHolder.getBean(UserService.class);
        User user = userService.getById((Long) map.get("userId"));

        if (user.getOpenId() != null) {
            map.put("userName", user.getNickName());
        } else {
            map.put("userName", user.getName());
        }

        map.put("orderCreateDate", DateUtil.format((Date) map.get("orderCreateDate"), "MM-dd HH:mm"));
        map.put("createDate", DateUtil.format((Date) map.get("createDate"), "MM-dd HH:mm"));
    }
}
