package net.novaborn.takeaway.goods.entity.services;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class GoodsStockServiceTest {
    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsStockService goodsStockService;

    @Test
    public void goodsStockTest() {
        goodsService.list().parallelStream().forEach(goods -> {
            Optional<GoodsStock> goodsStock = goodsStockService.getByGoodsId(goods.getId()).or(() -> {
                GoodsStock stockTemp = new GoodsStock();
                stockTemp.setGoodsId(goods.getId());
                stockTemp.setStock(-1);
                stockTemp.insert();
                return Optional.of(stockTemp);
            });
        });
    }
}


