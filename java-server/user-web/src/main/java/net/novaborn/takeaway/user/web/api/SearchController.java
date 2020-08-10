package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/search")
public class SearchController extends BaseController {
    private GoodsService goodsService;

    @PostMapping("searchGoods")
    @ResponseBody
    public List searchGoods(String keyword) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>().like("name", keyword);
        List<Goods> goodsList = goodsService.list(queryWrapper).stream().filter(item -> !item.getState().equals(GoodsState.OFF))
                .sorted(Comparator.comparing(Goods::getCreateDate).reversed().thenComparing(Goods::getName).thenComparing(Goods::getIndex).reversed())
                .collect(Collectors.toList());
        return (List) new GoodsWrapper(goodsList).warp();
    }
}
