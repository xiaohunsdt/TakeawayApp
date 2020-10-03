package net.novaborn.takeaway.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import net.novaborn.takeaway.user.UserApplication;
import net.novaborn.takeaway.user.service.impl.WxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserApplication.class})
public class WxServiceTest {
    @Autowired
    WxService wxService;

    @Autowired
    private WxMaService wxMaService;

    @Test
    public void setSessionKey() {
        wxService.setSessionKey("asdadzxcxzc", "aaaaaaa");
    }

    @Test
    public void getSessionKey() {
        System.out.println(wxService.getSessionKey("asdadzxcxzc"));
    }

    @Test
    @SneakyThrows
    public void sendSubscribeMessageKey() {
        List<WxMaSubscribeMessage.Data> dataList = new ArrayList();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "#11"));
        dataList.add(new WxMaSubscribeMessage.Data("phrase2", "制作中"));
        dataList.add(new WxMaSubscribeMessage.Data("amount3", "₩26000"));
        dataList.add(new WxMaSubscribeMessage.Data("phrase5", "一般订单"));
        dataList.add(new WxMaSubscribeMessage.Data("thing7", "已经确认收到您的订单,正在为您制作!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser("o9UA_5abDk-kn7KSaMAoriIlvg6c");
        subscribeMessage.setTemplateId("chtooPomhx0JrFECp0ZzYLlRZHc6tA7UdN-l5lAV0A4");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/main?state=WAIT_EAT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) { }
    }

    @Test
    @SneakyThrows
    public void sendSubscribeMessage2Key() {
        List<WxMaSubscribeMessage.Data> dataList = new ArrayList();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "#56"));
        dataList.add(new WxMaSubscribeMessage.Data("time2", "2020-05-05 12:23:10"));
        dataList.add(new WxMaSubscribeMessage.Data("thing3", "您的订单已经开始配送,请耐心等待!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser("o9UA_5abDk-kn7KSaMAoriIlvg6c");
        subscribeMessage.setTemplateId("4tz6mHc6JK5tsCq6lT2IGh2Leo46QyeDWjLml__PNI0");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/main?state=WAIT_EAT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) { }
    }

    @Test
    @SneakyThrows
    public void sendSubscribeMessage3Key() {
        List<WxMaSubscribeMessage.Data> dataList = new ArrayList();
        dataList.add(new WxMaSubscribeMessage.Data("character_string1", "#11"));
        dataList.add(new WxMaSubscribeMessage.Data("time2", "2020-05-05 12:23:10"));
        dataList.add(new WxMaSubscribeMessage.Data("thing3", "您的订单已完成,感谢您选择聚韩外卖!"));

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser("o9UA_5abDk-kn7KSaMAoriIlvg6c");
        subscribeMessage.setTemplateId("-fscbMujX6HY1jkr-Sy1bjqv1p6FDq9vkhGw-4hL9Xk");
        subscribeMessage.setData(dataList);
        subscribeMessage.setPage("/pages/order/main?state=WAIT_COMMENT");
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) { }
    }
}