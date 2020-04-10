package net.novaborn.takeaway.admin.web.wrapper;

import cn.hutool.core.date.DateUtil;
import net.novaborn.takeaway.common.BaseControllerWrapper;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.UserService;

import java.util.Date;
import java.util.Map;

/**
 * 分类包装类
 *
 * @author xiaohun
 */
public class OrderWrapperEx extends BaseControllerWrapper {

    public OrderWrapperEx(Object element) {
        super(element);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        UserService userService = SpringContextHolder.getBean(UserService.class);
        AddressService addressService = SpringContextHolder.getBean(AddressService.class);
        User user = userService.getById((String) map.get("userId"));
        Address address = addressService.getById((String) map.get("addressId"));

        if (user.getOpenId() != null) {
            map.put("userName", user.getNickName());
        } else {
            map.put("userName", user.getName());
        }

        map.put("address", address);

        Date createDate = (Date) map.get("createDate");
        Date appointmentDate = (Date) map.get("appointmentDate");

        if (appointmentDate != null) {
            map.put("appointmentDate", DateUtil.format(appointmentDate, "MM-dd HH:mm"));
        }
        map.put("createDate", DateUtil.format(createDate, "MM-dd HH:mm"));
    }
}
