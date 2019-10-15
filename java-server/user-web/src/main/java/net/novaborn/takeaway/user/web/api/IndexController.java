package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class IndexController extends BaseController {
    private GoodsService goodsService;


    @GetMapping("index")
    @ResponseBody
    public SuccessTip index() {
        return new SuccessTip();
    }

    @GetMapping("getSpecificFlagGoodsList")
    @ResponseBody
    public Object getSpecificFlagGoodsList(String flag) {
        return new GoodsWrapper(goodsService.getGoodsListByFlag(flag)).warp();
    }
}
