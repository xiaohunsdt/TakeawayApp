package net.novaborn.takeaway.admin.utils;

import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
class PrinterUtilTest {
    @Autowired
    PrinterUtil printerUtil;

    @Autowired
    OrderService orderService;

    @Test
    void test() {
        printerUtil.print(orderService.getById(1317384242112397313L));
    }

    @Test
    void test2() {
        printerUtil.setVoiceType("05SBD7NDCE6834B",0);
    }

    @Test
    void test3() {
        System.out.println(" 1");
        System.out.println("我");
        System.out.println("1");
        System.out.println("t");
    }
}