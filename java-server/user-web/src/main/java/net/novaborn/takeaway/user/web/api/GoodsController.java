package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.user.web.dto.GoodsListDto;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/goods")
public class GoodsController extends BaseController {

    GoodsService goodsService;

    @GetMapping("getByGoodsId")
    public ResponseEntity getByGoodsId(String id) {
        Goods goods = goodsService.getById(id);
        return ResponseEntity.ok(new GoodsWrapper(goods).warp());
    }

    @GetMapping("getGoodsListByCategoryId")
    public ResponseEntity getGoodsListByCategoryId(@RequestParam Long categoryId) {
        List<Goods> goodsList = goodsService.getGoodsListByCategoryId(categoryId);

        // 筛选有效商品
        goodsList = goodsList.stream()
                .filter(item -> !item.getState().equals(GoodsState.OFF))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new GoodsWrapper(goodsList).warp());
    }

    @GetMapping("getAllGoodsList")
    public ResponseEntity getAllGoodsList(Long storeId) {
        LambdaQueryWrapper<Goods> query = Wrappers.lambdaQuery();
        query.eq(Goods::getStoreId, storeId);

        List<Goods> goodsList = goodsService.list(query).stream()
                .filter(item -> !item.getState().equals(GoodsState.OFF))
                .sorted(Comparator.comparing(Goods::getCreateDate).reversed().thenComparing(Goods::getName).thenComparing(Goods::getIndex).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new GoodsListDto(goodsList));
    }
}
