package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.store.dao.IStoreDao;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.enums.State;
import net.novaborn.takeaway.store.service.IStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Service
public class StoreService extends ServiceImpl<IStoreDao, Store> implements IStoreService {
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
}
