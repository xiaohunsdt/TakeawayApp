package net.novaborn.takeaway.user.utils;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.order.entity.Order;
import net.novaborn.takeaway.order.utils.OrderFormatUtil;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter(onMethod_ = {@Autowired})
@Component
public class WxSubscrubeMessageUtil {
    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserService userService;

    public void sendOrderReceiveMessage(Order order) {
        User user = userService.getById(order.getUserId());

        List<WxMaSubscribeMessage.Data> dataList = new ArrayList<>();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "# " + order.getNumber().toString()));
        dataList.add(new WxMaSubscribeMessage.Data("phrase2", "制作中"));
        dataList.add(new WxMaSubscribeMessage.Data("amount3", "₩" + order.getRealPrice()));
        dataList.add(new WxMaSubscribeMessage.Data("phrase5", OrderFormatUtil.formatOrderType(order)));
        dataList.add(new WxMaSubscribeMessage.Data("thing7", "已经确认收到您的订单,正在为您制作!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser(user.getOpenId());
        subscribeMessage.setTemplateId("chtooPomhx0JrFECp0ZzYLlRZHc6tA7UdN-l5lAV0A4");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/index?state=WAIT_EAT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) {
            log.error("", e);
        }
    }

    public void sendOrderDeliveryMessage(Order order) {
        User user = userService.getById(order.getUserId());

        List<WxMaSubscribeMessage.Data> dataList = new ArrayList<>();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "# " + order.getNumber()));
        dataList.add(new WxMaSubscribeMessage.Data("time2", DateUtil.formatDateTime(order.getUpdateDate())));
        dataList.add(new WxMaSubscribeMessage.Data("thing3", "您的订单已经开始配送,请耐心等待!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser(user.getOpenId());
        subscribeMessage.setTemplateId("4tz6mHc6JK5tsCq6lT2IGh2Leo46QyeDWjLml__PNI0");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/index?state=WAIT_EAT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) {
            log.error("", e);
        }
    }

    public void sendOrderFinishedMessage(Order order) {
        User user = userService.getById(order.getUserId());

        List<WxMaSubscribeMessage.Data> dataList = new ArrayList<>();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "# " + order.getNumber()));
        dataList.add(new WxMaSubscribeMessage.Data("time2", DateUtil.formatDateTime(order.getUpdateDate())));
//        dataList.add(new WxMaSubscribeMessage.Data("thing3", "您的订单已完成,感谢您选择聚韩外卖!"));
        dataList.add(new WxMaSubscribeMessage.Data("thing3", "您的订单已完成,请到门口查看!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser(user.getOpenId());
        subscribeMessage.setTemplateId("-fscbMujX6HY1jkr-Sy1bjqv1p6FDq9vkhGw-4hL9Xk");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/index?state=WAIT_COMMENT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) {
            log.error("", e);
        }
    }
}
