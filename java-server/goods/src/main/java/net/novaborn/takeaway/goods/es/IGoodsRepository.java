package net.novaborn.takeaway.goods.es;

import net.novaborn.takeaway.goods.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * es 的操作类
 */
public interface IGoodsRepository extends ElasticsearchRepository<Goods, String> {

}