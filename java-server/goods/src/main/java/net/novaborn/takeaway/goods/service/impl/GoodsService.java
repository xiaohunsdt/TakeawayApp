package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.goods.dao.IGoodsDao;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.exception.GoodsExceptionEnum;
import net.novaborn.takeaway.goods.exception.GoodsStockExceptionEnum;
import net.novaborn.takeaway.goods.service.IGoodsService;
import net.novaborn.takeaway.goods.entity.Goods;
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
public class GoodsService extends ServiceImpl<IGoodsDao, Goods> implements IGoodsService {
    @Override
    public boolean updateById(Goods entity) {
        if (!super.updateById(entity)) {
            throw new SysException(GoodsExceptionEnum.UPDATE_FAILED);
        }

        return true;
    }

    @Override
    public Optional<Goods> selectByName(String name) {
        return this.baseMapper.selectByName(name);
    }

    @Override
    public List<Goods> getGoodsListByCategoryId(Long categoryId) {
        return this.baseMapper.getGoodsListByCategoryId(categoryId);
    }

    @Override
    public List<Goods> getGoodsListByFlag(String flag) {
        return this.baseMapper.getGoodsListByFlag(flag);
    }

    @Override
    public IPage<Goods> getGoodsListByPage(Page page, Map args) {
        return this.baseMapper.getGoodsListByPage(page, args);
    }
}
