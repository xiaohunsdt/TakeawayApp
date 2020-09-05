package net.novaborn.takeaway.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.store.dao.IStoreDao;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.service.IStoreService;
import org.springframework.stereotype.Service;

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
}
