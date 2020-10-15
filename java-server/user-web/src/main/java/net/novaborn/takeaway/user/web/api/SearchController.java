package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.user.web.wrapper.ProduceWrapper;
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
    private ProduceService produceService;

    @PostMapping("searchGoods")
    @ResponseBody
    public List searchGoods(String keyword) {
        QueryWrapper<Produce> queryWrapper = new QueryWrapper<Produce>().like("name", keyword);
        List<Produce> produceList = produceService.list(queryWrapper).stream().filter(item -> !item.getState().equals(ProduceState.OFF))
                .sorted(Comparator.comparing(Produce::getCreateDate).reversed().thenComparing(Produce::getName).thenComparing(Produce::getIndex).reversed())
                .collect(Collectors.toList());
        return (List) new ProduceWrapper(produceList).warp();
    }
}
