package net.novaborn.takeaway.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 产品的规格
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`produce_spec`")
public class ProduceSpec extends Model<ProduceSpec> {
    /**
     * 产品Id
     */
    @TableId(type = IdType.INPUT)
    private Long produceId;

    /**
     * 参数数组,json格式
     */
    private String options;
}
