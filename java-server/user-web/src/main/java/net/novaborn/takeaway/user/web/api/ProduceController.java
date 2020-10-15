package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.bean.BeanUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.goods.dto.GoodsDto;
import net.novaborn.takeaway.goods.dto.ProduceDto;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.enums.ProduceState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import net.novaborn.takeaway.user.web.dto.ProduceListDto;
import net.novaborn.takeaway.user.web.wrapper.ProduceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/produce")
public class ProduceController extends BaseController {

    ProduceService produceService;

    ProduceSpecService produceSpecService;

    GoodsService goodsService;

    GoodsStockService goodsStockService;

    @ResponseBody
    @GetMapping("getById")
    public Object getById(Long id) {
        return produceService.getById(id);
    }

    @ResponseBody
    @GetMapping("getDetailById")
    public ProduceDto getDetailById(Long id) {
        Produce produce = produceService.getById(id);
        ProduceSpec produceSpec = produceSpecService.getById(id);
        List<GoodsDto> goodsDtoList = goodsService.getByProduceId(id).stream().map(item -> {
            GoodsDto goodsDto = new GoodsDto();
            BeanUtil.copyProperties(item, goodsDto);
            goodsDto.setStock(goodsStockService.getByGoodsId(item.getId()).get().getStock());
            return goodsDto;
        }).collect(Collectors.toList());
        return new ProduceDto(produce, produceSpec, goodsDtoList);
    }

    @GetMapping("getListByCategoryId")
    public ResponseEntity getListByCategoryId(@RequestParam Long categoryId) {
        List<Produce> produceList = produceService.getListByCategoryId(categoryId);

        // 筛选有效商品
        produceList = produceList.stream()
                .filter(item -> !item.getState().equals(ProduceState.OFF))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ProduceWrapper(produceList).warp());
    }

    @GetMapping("getAllList")
    public ResponseEntity getAllList() {
        List<Produce> produceList = produceService.list().stream()
                .filter(item -> !item.getState().equals(ProduceState.OFF))
                    .sorted(Comparator.comparing(Produce::getCreateDate).reversed().thenComparing(Produce::getName).thenComparing(Produce::getIndex).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ProduceListDto(produceList));
    }
}
