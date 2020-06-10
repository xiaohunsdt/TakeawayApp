package net.novaborn.takeaway.goods.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.es.IGoodsRepository;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class IGoodsDaoEsTest {
    @Autowired
    GoodsService goodsService;

    @Autowired
    IGoodsRepository iGoodsRepository;

    @Test
    public void saveTest() {
        Goods goods = goodsService.getById("b5b18c4ca5fb0ca83b5a5afbb06e0c44");
        iGoodsRepository.save(goods);
    }
}