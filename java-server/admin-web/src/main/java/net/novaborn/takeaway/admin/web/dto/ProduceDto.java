package net.novaborn.takeaway.admin.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduceDto implements Serializable {

    @Valid
    @NotNull(message = "产品必须设置")
    private Produce produce;

    private ProduceSpec specs;

    @Valid
    @NotNull(message = "没有商品列表")
    @Size(min = 1, message = "没有商品列表")
    private List<GoodsDto> goodsList;
}
