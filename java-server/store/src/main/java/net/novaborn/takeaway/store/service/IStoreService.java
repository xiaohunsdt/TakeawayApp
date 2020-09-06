package net.novaborn.takeaway.store.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * 返回状态征程的店铺
     * @return
     */
    List<Store> getAllAvailableStore();
}
