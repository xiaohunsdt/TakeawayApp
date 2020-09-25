package net.novaborn.takeaway.admin.utils;

import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PrinterUtilTest {
    @Autowired
    PrinterUtil printerUtil;

    @Autowired
    OrderService orderService;

    @Test
    void test() {
        printerUtil.print(orderService.getById(1301895049803542529L));
    }
}