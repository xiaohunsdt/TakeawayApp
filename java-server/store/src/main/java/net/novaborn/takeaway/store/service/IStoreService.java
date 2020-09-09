package net.novaborn.takeaway.store.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.store.dto.AppointmentTimesDto;
import net.novaborn.takeaway.store.dto.ServiceStateDto;
import net.novaborn.takeaway.store.entity.Store;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IStoreService extends IService<Store> {
    /**
     * 分页获取店铺列表
     *
     * @param page 分页实例
     * @param args  name        名称
     * @return 店铺列表
     */
    IPage<Store> getListByPage(Page page, Map args);

    /**
     * 返回状态正常的店铺
     * @return
     */
    List<Store> getAllAvailableStore();

    /**
     * 返回指定店铺的服务状态
     *
     * @param storeId
     * @return
     */
    ServiceStateDto getServiceState(Long storeId);

    /**
     * 返回指定店铺是否允许对指定价格和地址的配送服务。
     *
     * @param storeId
     * @param addressId
     * @param allPrice
     * @return
     */
    ServiceStateDto getExpressServiceState(Long storeId, Long addressId, Integer allPrice);

    /**
     * 返回指定店铺的配送费
     *
     * @param storeId
     * @return
     */
    Integer getDeliveryPrice(Long storeId);

    /**
     * 获取指定地址与店的直线距离
     *
     * @param storeId   店铺Id
     * @param addressId
     * @return
     */
    Double getDistanceWithStore(Long storeId, Long addressId);

    /**
     * 返回指定店铺的预约时间列表集
     * @param storeId
     * @return
     */
    AppointmentTimesDto getAppointmentTimes(Long storeId);
}
