package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.banner.entity.Banner;
import net.novaborn.takeaway.banner.service.impl.BannerService;
import net.novaborn.takeaway.common.enums.From;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.user.web.wrapper.BannerWrapper;
import net.novaborn.takeaway.user.web.wrapper.ProduceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/index")
public class IndexController extends BaseController {
    private ProduceService produceService;

    private GoodsService goodsService;

    private BannerService bannerService;

    private static Map<From, String> fromerNotice;

    static {
        fromerNotice = new HashMap<>();
        fromerNotice.put(From.YONSEI, "让最圆的明月陪伴你和我，让月饼传达我们的心愿与祝福。延世学联祝你中秋佳节快乐，月圆人圆事事圆满!");
        fromerNotice.put(From.SOGANG, "让最圆的明月陪伴你和我，让月饼传达我们的心愿与祝福。西江学联祝你中秋佳节快乐，月圆人圆事事圆满!");
    }

    @GetMapping("getBannersList")
    public ResponseEntity getBannersList() {
        List<Banner> activities = bannerService.list()
            .stream()
            .filter(Banner::getIsShow)
            .sorted(Comparator.comparing(Banner::getIndex).reversed())
            .collect(Collectors.toList());
        return ResponseEntity.ok(new BannerWrapper(activities).warp());
    }

    @GetMapping("getSpecificFlagProduceList")
    @ResponseBody
    public Object getSpecificFlagProduceList(String flag) {
        List<Produce> goodsList = produceService.getListByFlag(flag);

        // 筛选有效商品
        goodsList = goodsList.stream()
            .filter(item -> !item.getState().equals(ProduceState.OFF))
            .collect(Collectors.toList());

        return new ProduceWrapper(goodsList).warp();
    }

    @GetMapping("getFormerNotice")
    @ResponseBody
    public Tip getFormerNotice(From from) {
        return new SuccessTip(fromerNotice.get(from));
//        return new SuccessTip();
    }
}
