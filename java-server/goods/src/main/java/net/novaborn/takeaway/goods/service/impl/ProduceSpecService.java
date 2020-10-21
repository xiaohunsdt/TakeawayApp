package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.dao.IProduceSpecDao;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.service.IProduceSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProduceSpecService extends ServiceImpl<IProduceSpecDao, ProduceSpec> implements IProduceSpecService {
    @Override
    public boolean checkSpecBeUsed(Long specId) {
        return this.baseMapper.getCountByspecId(specId) > 0;
    }
}
