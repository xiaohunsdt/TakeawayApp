package net.novaborn.takeaway.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.novaborn.takeaway.goods.dao.IGoodsDao;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaohun
 * @since 2018-07-12
 */
@Service
public class GoodsService extends ServiceImpl<IGoodsDao, Goods> implements IGoodsService {

}
