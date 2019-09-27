package net.novaborn.takeaway.goods.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.goods.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
public interface IGoodsDao extends BaseMapper<Goods> {
    Optional<Goods> selectByName(String name);

    IPage<Goods> getGoodsListByPage(Page page, @Param("args") Map args);
}
