package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.store.dto.AppointmentTimesDto;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.enums.State;
import net.novaborn.takeaway.store.exception.StoreExceptionEnum;
import net.novaborn.takeaway.store.service.impl.StoreService;
import net.novaborn.takeaway.user.entity.Coordinate;
import net.novaborn.takeaway.user.web.wrapper.StoreWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/user/store")
public class StoreController extends BaseController {

    StoreService storeService;

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
        List<Store> storeList = storeService.list().stream()
                .filter(item -> !item.getState().equals(State.OFF))
                .sorted(Comparator.comparing(Store::getCreateDate).reversed())
                .collect(Collectors.toList());
        return new StoreWrapper(storeList).warp();
    }

    @ResponseBody
    @GetMapping("getAllStoreList")
    public Object getAllStoreList() {
        List<Store> storeList = storeService.list().stream()
                .filter(item -> !item.getState().equals(State.OFF))
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

    @GetMapping("getAppointmentTimes")
    @ResponseBody
    public AppointmentTimesDto getAppointmentTimes(Long storeId) {
        return storeService.getAppointmentTimes(storeId);
    }
}
