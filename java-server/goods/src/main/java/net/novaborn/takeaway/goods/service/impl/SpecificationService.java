package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.dao.ISpecificationDao;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Specification;
import net.novaborn.takeaway.goods.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

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
public class SpecificationService extends ServiceImpl<ISpecificationDao, Specification> implements ISpecificationService {
    @Override
    public Optional<Specification> selectByKey(String key) {
        return this.baseMapper.selectByKey(key,null);
    }

    @Override
    public Optional<Specification> selectByKey(String key, Long storeId) {
        return this.baseMapper.selectByKey(key,storeId);
    }

    @Override
    public IPage<Specification> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }
}
