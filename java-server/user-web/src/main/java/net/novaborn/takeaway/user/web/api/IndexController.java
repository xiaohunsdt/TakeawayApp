package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.web.dto.GoodsPageSettingDto;
import net.novaborn.takeaway.user.web.dto.ServiceStateDto;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private AddressService addressService;

    private SettingService settingService;

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

    @GetMapping("getServiceState")
    @ResponseBody
    public Object getServiceState() {
        Setting service_running = settingService.getSettingByName("service_running", SettingScope.SYSTEM);
        Setting service_close_notice = settingService.getSettingByName("service_close_notice", SettingScope.SYSTEM);
        if (!Boolean.parseBoolean((String) service_running.getValue())) {
            return new ServiceStateDto(-1, (String) service_close_notice.getValue());
        }
        return new ServiceStateDto();
    }


    @GetMapping("getExpressServiceState")
    @ResponseBody
    public Object getExpressServiceState(@RequestParam String addressId, @RequestParam Integer allPrice) {
        Setting maxExpressDistance = settingService.getSettingByName("max_express_distance", SettingScope.EXPRESS);
        double distance = addressService.getDistanceWithStore(addressId);

        if (distance > Integer.parseInt((String) maxExpressDistance.getValue())) {
            return new ServiceStateDto(-1, "您的距离太远，超出了我们的配送范围!!");
        }

        // 大于3公里，价格小于30000
        if (distance > 3000 && allPrice < 30000) {
            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 30000));
        }

        // 大于2公里，价格小于10000
        if (distance > 2000 && allPrice < 10000) {
            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 10000));
        }

        return new ServiceStateDto();
    }
}
