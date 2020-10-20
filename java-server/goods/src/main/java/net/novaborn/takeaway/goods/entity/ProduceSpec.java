package net.novaborn.takeaway.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品的规格
 *
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`produce_spec`")
@AllArgsConstructor
@NoArgsConstructor
public class ProduceSpec extends Model<ProduceSpec> {
    /**
     * 产品Id
     */
    @TableId(type = IdType.INPUT)
    private Long produceId;

    private String selectedSpecs;

    /**
     * 参数数组,json格式
     */
    @TableField(value = "`options`")
    private String options;
}
