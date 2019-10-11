package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/goods")
public class GoodsController extends BaseController {

    GoodsService goodsService;

    @GetMapping("getByGoodsId")
    public ResponseEntity getByGoodsId(String id) {
        Goods goods = goodsService.getById(id);
        return ResponseEntity.ok(goods);
    }

    @GetMapping("getAllGoods")
    public ResponseEntity getAllGoods() {
        List<Goods> GoodsList = goodsService.list();
        return ResponseEntity.ok(GoodsList);
    }
}
