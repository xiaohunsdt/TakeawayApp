package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.web.dto.AppointmentTimesDto;
import net.novaborn.takeaway.user.web.dto.ServiceStateDto;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
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
        if (!Boolean.parseBoolean(service_running.getValue())) {
            return new ServiceStateDto(-1, service_close_notice.getValue());
        }
        return new ServiceStateDto();
    }


    @GetMapping("getExpressServiceState")
    @ResponseBody
    public Object getExpressServiceState(@RequestParam String addressId, @RequestParam Integer allPrice) {
        int maxExpressDistance = Integer.parseInt(settingService.getSettingByName("max_express_distance", SettingScope.EXPRESS).getValue());
        double distance = addressService.getDistanceWithStore(addressId);

        // 10000 以下不配送
//        if (allPrice < 10000) {
//            return new ServiceStateDto(-1,"今日85折,低于10000韩币无法配送!!");
//        }

        if (distance > maxExpressDistance) {
            return new ServiceStateDto(-1, "您的距离太远，超出了我们的配送范围!!");
        }

        // 大于3公里，价格小于30000
        if (distance > 2800 && allPrice < 30000) {
            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 30000));
        }

        // 大于2公里，价格小于10000
        if (distance > 1500 && allPrice < 10000) {
            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 10000));
        }

        return new ServiceStateDto();
    }

    @GetMapping("getAppointmentTimes")
    @ResponseBody
    public Object getAppointmentTimes() {
        Date currentDate = DateUtil.date();
        String store_open_date = settingService.getSettingByName("store_open_date", SettingScope.STORE).getValue();
        String store_open_time = settingService.getSettingByName("store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName("store_close_time", SettingScope.STORE).getValue();
        DateTime storeOpenTime = DateUtil.parseDateTime(store_open_time);
        DateTime storeCloseTime = DateUtil.parseDateTime(store_close_time);
        List<Map<String, Date>> timePairs = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                currentDate = DateUtil.offsetDay(currentDate, 1)
                        .setField(DateField.HOUR_OF_DAY, 0)
                        .setField(DateField.MINUTE, 0)
                        .setField(DateField.SECOND, 0);
            }
            // 指定日期是否营业
            if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
                continue;
            }

            Date startDate;
            Date endDate;
            if (TimeUtil.isBetween(currentDate, store_open_time, store_close_time)) {
                startDate = currentDate;
                endDate = new DateTime(currentDate)
                        .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
                        .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
                        .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));
            } else if (TimeUtil.isBefore(currentDate, storeOpenTime)) {
                startDate = new DateTime(currentDate)
                        .setField(DateField.HOUR_OF_DAY, storeOpenTime.getField(DateField.HOUR_OF_DAY))
                        .setField(DateField.MINUTE, storeOpenTime.getField(DateField.MINUTE))
                        .setField(DateField.SECOND, storeOpenTime.getField(DateField.SECOND));
                endDate = new DateTime(currentDate)
                        .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
                        .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
                        .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));
            } else {
                continue;
            }

            // 预定要提前3个小时
            startDate = DateTime.of(currentDate).offset(DateField.HOUR_OF_DAY, 3);
            if (TimeUtil.isAfter(startDate, endDate)) {
                continue;
            }

            Map<String, Date> timePair = new HashMap<>();
            timePair.put("start", startDate);
            timePair.put("end", endDate);
            timePairs.add(timePair);
        }
        return new AppointmentTimesDto(timePairs);
    }
}
