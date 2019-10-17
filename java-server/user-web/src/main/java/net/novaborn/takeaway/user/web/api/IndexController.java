package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/index")
public class IndexController extends BaseController {
    private GoodsService goodsService;

    @GetMapping("getSpecificFlagGoodsList")
    @ResponseBody
    public Object getSpecificFlagGoodsList(String flag) {
        List<Goods> goodsList = goodsService.getGoodsListByFlag(flag);

        // 筛选有效商品
        goodsList = goodsList.stream()
                .filter(item -> !item.getState().equals(GoodsState.OFF))
                .collect(Collectors.toList());

        return new GoodsWrapper(goodsList).warp();
    }
}
