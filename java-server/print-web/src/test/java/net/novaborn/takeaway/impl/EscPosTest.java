package net.novaborn.takeaway.impl;

import net.novaborn.takeaway.print.PrintApplication;
import net.novaborn.takeaway.print.web.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PrintApplication.class})
public class EscPosTest {

    @Autowired
    IndexController indexController;


    @Test
    public void testPrint() throws Exception {
        indexController.printOrder(1307285324024635394L);
    }
}
