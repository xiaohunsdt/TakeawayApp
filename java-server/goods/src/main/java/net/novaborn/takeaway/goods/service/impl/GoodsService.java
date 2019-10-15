package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.goods.dao.IGoodsDao;
import net.novaborn.takeaway.goods.service.IGoodsService;
import net.novaborn.takeaway.goods.entity.Goods;
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
@Service
public class GoodsService extends ServiceImpl<IGoodsDao, Goods> implements IGoodsService {
    @Override
    public Optional<Goods> selectByName(String name) {
        return this.baseMapper.selectByName(name);
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
