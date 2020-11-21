package net.novaborn.takeaway.store.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.entity.BaseKVO;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.MapDistanceUtil;
import net.novaborn.takeaway.common.utils.TimeUtil;
import net.novaborn.takeaway.store.dao.IStoreDao;
import net.novaborn.takeaway.store.dto.ServiceStateDto;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.enums.State;
import net.novaborn.takeaway.store.service.IStoreService;
import net.novaborn.takeaway.system.entity.Setting;
import net.novaborn.takeaway.system.enums.SettingScope;
import net.novaborn.takeaway.system.service.impl.SettingService;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.exception.AddressExceptionEnum;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class StoreService extends ServiceImpl<IStoreDao, Store> implements IStoreService {

    private SettingService settingService;

    private AddressService addressService;

    @Override
    public IPage<Store> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }

    @Override
    public List<Store> getAllAvailableStore() {
        Store store = new Store();
        store.setState(State.ON);
        return this.list(Wrappers.query(store));
    }

    @Override
    public ServiceStateDto getServiceState(Long storeId) {
        Setting service_running = settingService.getSettingByName(storeId, "service_running", SettingScope.SYSTEM);
        Setting service_close_notice = settingService.getSettingByName(storeId, "service_close_notice", SettingScope.SYSTEM);

        Date currentDate = DateUtil.date();
        String store_open_date = settingService.getSettingByName(storeId, "store_open_date", SettingScope.STORE).getValue();
        String store_open_time = settingService.getSettingByName(storeId, "store_open_time", SettingScope.STORE).getValue();
        String store_close_time = settingService.getSettingByName(storeId, "store_close_time", SettingScope.STORE).getValue();

        if (!Boolean.parseBoolean(service_running.getValue())) {
            return new ServiceStateDto(-1, service_close_notice.getValue());
        }

        if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
            return new ServiceStateDto(State.CLOSED.getCode(), "今日休息!不营业");
        }

        if (!TimeUtil.isBetween(currentDate, store_open_time, store_close_time)) {
            return new ServiceStateDto(State.CLOSED.getCode(), "当前不在营业时间段!");
        }

        return new ServiceStateDto(State.ON.getCode(), null);
    }

    @Override
    public ServiceStateDto getExpressServiceState(Long storeId, Long addressId, Integer allPrice) {
        int lowestOrderPrice = settingService.getSettingByName(storeId, "lowest_order_price", SettingScope.DELIVERY).getValueAsInt();
        int maxExpressDistance = settingService.getSettingByName(storeId, "max_delivery_distance", SettingScope.DELIVERY).getValueAsInt();
        List<BaseKVO<Integer, Integer>> distancePriceArr = settingService.getDistancePriceArr(storeId);
        double distance = this.getDistanceWithStore(storeId, addressId);

        // 最低配送价格
        if (allPrice < lowestOrderPrice) {
            return new ServiceStateDto(-1, String.format("低于%d韩币无法配送!!", lowestOrderPrice));
        }

        if (distance >= maxExpressDistance) {
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

    @Override
    public Integer getDeliveryPrice(Long storeId) {
        return settingService.getSettingByName(storeId, "delivery_price", SettingScope.DELIVERY).getValueAsInt();
    }

    @Override
    public Double getDistanceWithStore(Long storeId, Long addressId) {
        Optional<Address> address = Optional.ofNullable(addressService.getById(addressId));

        address.orElseThrow(() -> new SysException(AddressExceptionEnum.NO_ADDRESS_ERROR));

        if (address.get().getX() == null) {
            throw new SysException(AddressExceptionEnum.ADDRESS_NO_COORDINATE_ERROR);
        }

        Store store = this.getById(storeId);

        if (store.getX() == null || store.getY() == null) {
            throw new SysException(AddressExceptionEnum.STORE_ADDRESS_NO_COORDINATE_ERROR);
        }

        return MapDistanceUtil.getDistance(
            address.get().getX(),
            address.get().getY(),
            store.getX(),
            store.getY()
        );
    }

//    @Override
//    public AppointmentTimesDto getAppointmentTimes(Long storeId) {
//        Date currentDate = DateUtil.date();
//        String store_open_date = settingService.getSettingByName(storeId, "store_open_date", SettingScope.STORE).getValue();
//        String store_open_time = settingService.getSettingByName(storeId, "store_open_time", SettingScope.STORE).getValue();
//        String store_close_time = settingService.getSettingByName(storeId, "store_close_time", SettingScope.STORE).getValue();
//        DateTime storeOpenTime = DateUtil.parseDateTime(store_open_time);
//        DateTime storeCloseTime = DateUtil.parseDateTime(store_close_time);
//        List<Map<String, Date>> timePairs = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            if (i != 0) {
//                currentDate = DateUtil.offsetDay(currentDate, 1)
//                    .setField(DateField.HOUR_OF_DAY, 0)
//                    .setField(DateField.MINUTE, 0)
//                    .setField(DateField.SECOND, 0);
//            }
//            // 指定日期是否营业
//            if (!store_open_date.contains(String.valueOf(DateUtil.dayOfWeek(currentDate)))) {
//                continue;
//            }
//
//            Date startDate;
//            Date endDate;
//            if (i == 0 && TimeUtil.isBetween(currentDate, store_open_time, store_close_time)) {
//                // 预定要提前2个小时
//                startDate = new DateTime(currentDate).offset(DateField.HOUR_OF_DAY, 2);
//                endDate = new DateTime(currentDate)
//                    .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
//                    .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
//                    .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));
//
//                if (startDate.getTime() > endDate.getTime()) {
//                    endDate = DateUtil.offsetDay(endDate, 1);
//                }
//            } else if (TimeUtil.isBefore(currentDate, storeOpenTime)) {
//                startDate = new DateTime(currentDate)
//                    .setField(DateField.HOUR_OF_DAY, storeOpenTime.getField(DateField.HOUR_OF_DAY))
//                    .setField(DateField.MINUTE, storeOpenTime.getField(DateField.MINUTE))
//                    .setField(DateField.SECOND, storeOpenTime.getField(DateField.SECOND))
//                    .offset(DateField.MINUTE, 30);
//                endDate = new DateTime(currentDate)
//                    .setField(DateField.HOUR_OF_DAY, storeCloseTime.getField(DateField.HOUR_OF_DAY))
//                    .setField(DateField.MINUTE, storeCloseTime.getField(DateField.MINUTE))
//                    .setField(DateField.SECOND, storeCloseTime.getField(DateField.SECOND));
//
//                if (startDate.getTime() > endDate.getTime()) {
//                    endDate = DateUtil.offsetDay(endDate, 1);
//                }
//
//                long diffMinutes = DateUtil.between(currentDate, startDate, DateUnit.MINUTE);
//                if (diffMinutes < 120) {
//                    startDate = DateTime.of(startDate).offset(DateField.MINUTE, 120 - (int) diffMinutes);
//                }
//            } else {
//                continue;
//            }
//
//            // 最迟截止下单日期的后30分钟到达
//            endDate = DateTime.of(endDate).offset(DateField.MINUTE, 30);
//
//            if (startDate.after(endDate)) {
//                continue;
//            }
//
//            Map<String, Date> timePair = new HashMap<>();
//            timePair.put("start", startDate);
//            timePair.put("end", endDate);
//            timePairs.add(timePair);
//        }
//        return new AppointmentTimesDto(timePairs, TimeUtil.isBetween(store_open_time, store_close_time));
//    }
}
