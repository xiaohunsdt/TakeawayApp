package net.novaborn.takeaway.goods.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.novaborn.takeaway.goods.dto.GoodsDto;
import net.novaborn.takeaway.goods.entity.Goods;

import java.util.List;

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
     *
     * @param produceId 产品Id
     * @return 查询到的产品商品
     */
    List<Goods> getByProduceId(Long produceId);

    /**
     * 根据产品id获取指定产品的商品数量
     *
     * @param produceId 产品Id
     * @return 查询到的商品数量
     */
    int getCountByProduceId(Long produceId);

    /**
     * 根据产品id生成指定的商品
     * @param produceId
     * @param goodsDtoList
     * @return 是否成功
     */
    boolean saveByProduceId(Long produceId, List<GoodsDto> goodsDtoList);

    /**
     * 根据产品id删除指定商品, 同时删除对应的库存数据
     *
     * @param produceId 产品Id
     * @return 是否成功
     */
    boolean deleteByProduceId(Long produceId);
}
