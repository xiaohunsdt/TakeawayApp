package net.novaborn.takeaway.goods.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class GoodsTest {
    @Autowired
    GoodsService goodsService;

    @Test
    public void createGoodsTest() {
        Goods goods = new Goods();
        goods.setName("蒜蓉黄瓜");
        goods.setDesc("美味好吃的蒜蓉黄瓜!");
        goods.setThumb("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569665705&di=2ba082452b0304e60a4b4d2bf58cc23d&imgtype=jpg&er=1&src=http%3A%2F%2Fpic16.nipic.com%2F20110820%2F128199_122056177000_2.jpg");
        goods.setCategoryId("8410fe3eac3dd72c7b0aeb4f24cc05a8");
        goods.setPrice(6000);
        goodsService.save(goods);
    }

    @Test
    public void getGoodsTest() {
        Goods goods = goodsService.getById("761b1144c87e8be15d8ab3356c71556b");
        System.out.println(goods);
    }

    @Test
    public void updateGoodsTest() {
        Goods goods = goodsService.getById("761b1144c87e8be15d8ab3356c71556b");
        goods.setName("蒜蓉黄瓜1");
        goods.setState(GoodsState.OFF);
        goods.updateById();
    }
}