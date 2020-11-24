package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.MapDistanceUtil;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.order.enums.OrderType;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.web.dto.AppointmentTimesDto;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.enums.State;
import net.novaborn.takeaway.store.exception.StoreExceptionEnum;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.user.entity.Coordinate;
import net.novaborn.takeaway.user.web.wrapper.StoreWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/store")
public class StoreController extends BaseController {
    private final int MAX_RADIUS = 5;

    private StoreService storeService;

    private SettingService settingService;

    @ResponseBody
    @PostMapping("getStoreById")
    public Object getStoreById(Long storeId) {
        Store store = storeService.getById(storeId);
        if (store == null) {
            throw new SysException(StoreExceptionEnum.STORE_NOT_EXIST);
        }
        return new StoreWrapper(store).warp();
    }

    @ResponseBody
    @PostMapping("getAvailableStoreList")
    public Object getAvailableStoreList(@Validated Coordinate coordinate) {
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(geo.makePoint(coordinate.getX(), coordinate.getY()), MAX_RADIUS * DistanceUtils.KM_TO_DEG, geo, null);

        LambdaQueryWrapper<Store> query = Wrappers.lambdaQuery();
        query.eq(Store::getState, State.ON)
                .between(Store::getX, rectangle.getMinX(), rectangle.getMaxX())
                .between(Store::getY, rectangle.getMinY(), rectangle.getMaxY());

        List<Store> storeList = storeService.list(query).stream()
                .filter(store -> MapDistanceUtil.getDistance(coordinate.getX(), coordinate.getY(), store.getX(), store.getY()) < store.getMaxDeliveryDistance())
                .sorted(Comparator.comparing(Store::getCreateDate))
                .collect(Collectors.toList());
        return new StoreWrapper(storeList).warp();
    }

    @ResponseBody
    @GetMapping("getAllStoreList")
    public Object getAllStoreList() {
        LambdaQueryWrapper<Store> query = Wrappers.lambdaQuery();
        query.eq(Store::getState, State.ON);

        List<Store> storeList = storeService.list(query).stream()
                .sorted(Comparator.comparing(Store::getCreateDate).reversed())
                .collect(Collectors.toList());
        return new StoreWrapper(storeList).warp();
    }

    @PostMapping("getServiceState")
    @ResponseBody
    public Object getServiceState(Long storeId) {
        return storeService.getServiceState(storeId);
    }

    @GetMapping("getExpressServiceState")
    @ResponseBody
    public Object getExpressServiceState(Long storeId, Long addressId, Integer allPrice) {
        return storeService.getExpressServiceState(storeId, addressId, allPrice);
    }

    @GetMapping("getDeliveryPrice")
    @ResponseBody
    public Integer getDeliveryPrice(Long storeId) {
        return storeService.getDeliveryPrice(storeId);
    }

    @PostMapping("getAppointmentTimes")
    @ResponseBody
    public AppointmentTimesDto getAppointmentTimes(Long storeId, OrderType orderType) {
        Date currentDate = DateUtil.date();
        String store_open_date = settingService.getSettingByName(storeId,"store_open_date", SettingScope.STORE).getValue();
        String store_open_time = settingService.getSettingByName(storeId,"store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName(storeId,"store_close_time", SettingScope.STORE).getValue();
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

            DateTime startDate;
            DateTime endDate;
            if (i == 0 && TimeUtil.isBetween(currentDate, store_open_time, store_close_time)) {
                // 预定要提前2个小时
                if (orderType == OrderType.APPOINTMENT || orderType == OrderType.NORMAL) {
                    startDate = new DateTime(currentDate).offset(DateField.HOUR_OF_DAY, 2);
//                    startDate = new DateTime(currentDate).offset(DateField.MINUTE, 20);
                } else {
                    startDate = new DateTime(currentDate).offset(DateField.MINUTE, 20);
                }
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
                    .setField(DateField.SECOND, storeOpenTime.getField(DateField.SECOND));
                if (orderType == OrderType.APPOINTMENT || orderType == OrderType.NORMAL) {
                    startDate.offset(DateField.MINUTE, 20);
                } else {
                    startDate.offset(DateField.MINUTE, 10);
                }
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
}
