package net.novaborn.takeaway.goods.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 根据产品id获取指定产品的商品
     * @param produceId 产品Id
     * @return 查询到的产品商品
     */
    List<Goods> getByProduceId(@Param("produceId") Long produceId);

    /**
     * 根据产品id获取指定产品的商品数量
     * @param produceId 产品Id
     * @return 查询到的商品数量
     */
    int getCountByProduceId(@Param("produceId") Long produceId);
}
