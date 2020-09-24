package net.novaborn.takeaway.pay.services.impl;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import net.novaborn.takeaway.pay.PayApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PayApplication.class})
class PayServiceTest {

    @Autowired
    PayService payService;

    @Test
    void createPayInfo() {
        WxPayMpOrderResult result = payService.createPayInfo("op37U5F3e01dNxNK4RM3nAMd78Xk", "243b155f0cf366476e1f13396115e772", "localhost");
        System.out.println(result);
    }
}