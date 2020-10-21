package net.novaborn.takeaway.produce;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.dto.GoodsDto;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class ProduceServiceTest {
    @Autowired
    ProduceService produceService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ProduceSpecService produceSpecService;

    @Test
    public void produceTest() {
        produceService.list().forEach(produce -> {
            ProduceSpec produceSpec = produceSpecService.getById(produce.getId());
            if (produceSpec == null) {
                produceSpec = new ProduceSpec(produce.getId(), "{}", "{}");
                produceSpec.insert();
            }
            if (goodsService.getCountByProduceId(produce.getId()) == 0) {
                GoodsDto goodsDto = new GoodsDto();
                goodsDto.setPrice(1);
                goodsDto.setStock(-1);
                goodsService.saveByProduceId(produce.getId(), List.of(goodsDto));
            }
        });
    }
}

