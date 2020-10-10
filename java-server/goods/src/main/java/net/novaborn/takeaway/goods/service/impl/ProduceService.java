package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.goods.dao.IProduceDao;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.exception.GoodsExceptionEnum;
import net.novaborn.takeaway.goods.service.IProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class ProduceService extends ServiceImpl<IProduceDao, Produce> implements IProduceService {
    @Override
    public boolean updateById(Produce entity) {
        if (!super.updateById(entity)) {
            throw new SysException(GoodsExceptionEnum.UPDATE_FAILED);
        }

        return true;
    }

    @Override
    public Optional<Produce> selectByName(String name) {
        return this.baseMapper.selectByName(name);
    }

    @Override
    public List<Produce> getListByCategoryId(Long categoryId) {
        return this.baseMapper.getListByCategoryId(categoryId);
    }

    @Override
    public List<Produce> getListByFlag(String flag) {
        return this.baseMapper.getListByFlag(flag);
    }

    @Override
    public IPage<Produce> getListByPage(Page page, Map args) {
        return this.baseMapper.getListByPage(page, args);
    }
}
