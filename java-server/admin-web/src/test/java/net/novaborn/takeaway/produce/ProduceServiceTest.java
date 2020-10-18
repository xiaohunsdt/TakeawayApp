package net.novaborn.takeaway.produce;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import net.novaborn.takeaway.order.service.impl.OrderItemService;
import net.novaborn.takeaway.order.service.impl.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class ProduceServiceTest {
    @Autowired
    ProduceService produceService;

    @Autowired
    ProduceSpecService produceSpecService;

    @Test
    public void getGoodsTest() {
        produceService.list().forEach(produce -> {
            ProduceSpec produceSpec = produceSpecService.getById(produce.getId());
            if (produceSpec == null) {
                produceSpec = new ProduceSpec(produce.getId(),"{}","{}");
                produceSpec.insert();
            }
        });
    }
}

