package net.novaborn.takeaway.admin;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminApplicationTest {
    @Test
    void test() {
        System.out.println(DateUtil.date().getField(DateField.HOUR_OF_DAY));
    }
}