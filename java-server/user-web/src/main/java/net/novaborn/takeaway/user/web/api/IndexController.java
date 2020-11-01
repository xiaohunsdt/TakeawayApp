package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.banner.entity.Banner;
import net.novaborn.takeaway.banner.service.impl.BannerService;
import net.novaborn.takeaway.common.entity.BaseKVO;
import net.novaborn.takeaway.common.enums.From;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.web.dto.AppointmentTimesDto;
import net.novaborn.takeaway.user.web.dto.ServiceStateDto;
import net.novaborn.takeaway.user.web.wrapper.BannerWrapper;
import net.novaborn.takeaway.user.web.wrapper.ProduceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProduceService produceService;

    private GoodsService goodsService;

    private AddressService addressService;

    private SettingService settingService;

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
    public Object getExpressServiceState(@RequestParam Long addressId, @RequestParam Integer allPrice) {
        int lowestOrderPrice = settingService.getSettingByName("lowest_order_price", SettingScope.DELIVERY).getValueAsInt();
        int maxDeliveryDistance = settingService.getSettingByName("max_delivery_distance", SettingScope.DELIVERY).getValueAsInt();
        List<BaseKVO<Integer, Integer>> distancePriceArr = settingService.getDistancePriceArr();
        double distance = addressService.getDistanceWithStore(addressId);

        // 最低配送价格
        if (allPrice < lowestOrderPrice) {
            return new ServiceStateDto(-1, String.format("低于%d韩币无法配送!!", lowestOrderPrice));
        }

        if (distance >= maxDeliveryDistance) {
            return new ServiceStateDto(-1, "您的距离太远，超出了我们的配送范围!!");
        }


        // 距离对应价格设置
        for (BaseKVO<Integer, Integer> item : distancePriceArr) {
            if (distance >= item.getKey() && allPrice < item.getValue()) {
                return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, item.getValue()));
            }
        }
//        // 大于3公里，价格小于30000
//        if (distance >= 2400 && allPrice < 30000) {
//            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 30000));
//        }
//
//        // 大于2公里，价格小于18000
//        if (distance > 1800 && allPrice < 18000) {
//            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 18000));
//        }
//
//        // 大于1公里，价格小于10000
//        if (distance > 1000 && allPrice < 10000) {
//            return new ServiceStateDto(-1, String.format("您当前距离本店%d米，需要点至少点 ₩%d 才能配送!!", (int) distance, 10000));
//        }

        return new ServiceStateDto();
    }

    @GetMapping("getDeliveryPrice")
    @ResponseBody
    public Integer getDeliveryPrice() {
        return settingService.getSettingByName("delivery_price", SettingScope.DELIVERY).getValueAsInt();
    }

    @GetMapping("getAppointmentTimes")
    @ResponseBody
    public AppointmentTimesDto getAppointmentTimes() {
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
            if (i == 0 && TimeUtil.isBetween(currentDate, store_open_time, store_close_time)) {
                // 预定要提前2个小时
                startDate = new DateTime(currentDate).offset(DateField.HOUR_OF_DAY, 2);
                endDate = new DateTime(currentDate)
                    .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
                    .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
                    .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));

                if (startDate.getTime() > endDate.getTime()) {
                    endDate = DateUtil.offsetDay(endDate, 1);
                }
            } else if (TimeUtil.isBefore(currentDate, storeOpenTime)) {
                startDate = new DateTime(currentDate)
                    .setField(DateField.HOUR_OF_DAY, storeOpenTime.getField(DateField.HOUR_OF_DAY))
                    .setField(DateField.MINUTE, storeOpenTime.getField(DateField.MINUTE))
                    .setField(DateField.SECOND, storeOpenTime.getField(DateField.SECOND))
                    .offset(DateField.MINUTE, 30);
                endDate = new DateTime(currentDate)
                    .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
                    .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
                    .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));

                if (startDate.getTime() > endDate.getTime()) {
                    endDate = DateUtil.offsetDay(endDate, 1);
                }

                long diffMinutes = DateUtil.between(currentDate, startDate, DateUnit.MINUTE);
                if (diffMinutes < 120) {
                    startDate = DateTime.of(startDate).offset(DateField.MINUTE, 120 - (int) diffMinutes);
                }
            } else {
                continue;
            }

            // 最迟截止下单日期的后30分钟到达
            endDate = DateTime.of(endDate).offset(DateField.MINUTE, 30);

            if (startDate.after(endDate)) {
                continue;
            }

            Map<String, Date> timePair = new HashMap<>();
            timePair.put("start", startDate);
            timePair.put("end", endDate);
            timePairs.add(timePair);
        }
        return new AppointmentTimesDto(timePairs, TimeUtil.isBetween(store_open_time, store_close_time));
    }

    @GetMapping("getFormerNotice")
    @ResponseBody
    public Tip getFormerNotice(From from) {
        return new SuccessTip(fromerNotice.get(from));
//        return new SuccessTip();
    }
}
