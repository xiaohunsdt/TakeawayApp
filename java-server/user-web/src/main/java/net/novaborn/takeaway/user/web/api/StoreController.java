package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.MapDistanceUtil;
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
    private final int MAX_RADIUS = 5;

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
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(geo.makePoint(coordinate.getX(), coordinate.getY()), MAX_RADIUS * DistanceUtils.KM_TO_DEG, geo, null);

        LambdaQueryWrapper<Store> query = Wrappers.lambdaQuery();
        query.eq(Store::getState, State.ON)
                .between(Store::getX, rectangle.getMinX(), rectangle.getMaxX())
                .between(Store::getY, rectangle.getMinY(), rectangle.getMaxY());

        List<Store> storeList = storeService.list(query).stream()
                .filter(store -> MapDistanceUtil.getDistance(coordinate.getX(), coordinate.getY(), store.getX(), store.getY()) < store.getMaxDeliveryDistance())
                .sorted(Comparator.comparing(Store::getCreateDate).reversed())
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

    @GetMapping("getAppointmentTimes")
    @ResponseBody
    public AppointmentTimesDto getAppointmentTimes(Long storeId) {
        return storeService.getAppointmentTimes(storeId);
    }
}
