package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.web.wrapper.ProduceWrapper;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.dto.GoodsDto;
import net.novaborn.takeaway.goods.dto.ProduceDto;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.entity.ProduceSpec;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/produce")
public class ProduceController extends BaseController {

    ProduceService produceService;

    ProduceSpecService produceSpecService;

    GoodsService goodsService;

    GoodsStockService goodsStockService;

    @ResponseBody
    @GetMapping("getById")
    public Object getById(Long id) {
        Produce produce = produceService.getById(id);
        return produce;
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

    @GetMapping("getAll")
    public ResponseEntity getAll() {
        List<Produce> produceList = produceService.list();
        return ResponseEntity.ok(produceList);
    }

    @PostMapping("getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) produceService.getListByPage(page, args);
        page.setRecords((List) new ProduceWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("create")
    @Transactional(rollbackFor = Exception.class)
    public Tip create(@RequestBody @Validated ProduceDto produceDto) {
        Optional<Produce> tempProduce = produceService.selectByName(produceDto.getProduce().getName());
        if (tempProduce.isPresent()) {
            return new ErrorTip(-1, "存在同名产品!");
        }

        produceService.save(produceDto.getProduce());
        Long produceId = produceDto.getProduce().getId();

        produceDto.getSpecs().setProduceId(produceId);
        produceSpecService.save(produceDto.getSpecs());

        goodsService.saveByProduceId(produceId, produceDto.getGoodsList());
        return new SuccessTip("创建成功!");
    }

    @ResponseBody
    @PostMapping("update")
    @Transactional(rollbackFor = Exception.class)
    public Tip update(@RequestBody @Validated ProduceDto produceDto) {
        Optional<Produce> targetProduce = Optional.ofNullable(produceService.getById(produceDto.getProduce().getId()));
        Optional<ProduceSpec> targetProduceSpec = Optional.ofNullable(produceSpecService.getById(produceDto.getProduce().getId()));

        if (targetProduce.isEmpty() || targetProduceSpec.isEmpty()) {
            return new ErrorTip(-1, "没有此产品或规格!");
        }

        if (produceDto.getSpecs().getOptions().equals(targetProduceSpec.get().getOptions())) {
            produceDto.getGoodsList().forEach(goodsDto -> {
                Goods goods = goodsService.getById(goodsDto.getId());
                BeanUtil.copyProperties(goodsDto, goods, CopyOptions.create().setIgnoreNullValue(true));
                goodsService.updateById(goods);

                GoodsStock stock = goodsStockService.getByGoodsId(goods.getId()).get();
                stock.setStock(goodsDto.getStock());

                if (!goods.getState().equals(GoodsState.ON)) {
                    stock.setStock(0);
                }
                if (goods.getState().equals(GoodsState.ON) && goodsDto.getStock() == 0) {
                    stock.setStock(-1);
                }
                goodsStockService.updateById(stock);
            });
        } else {
            // spec发生变化，删除原有商品，重新生成新商品
            goodsService.deleteByProduceId(targetProduce.get().getId());
            goodsService.saveByProduceId(targetProduce.get().getId(), produceDto.getGoodsList());
        }

        BeanUtil.copyProperties(produceDto.getSpecs(), targetProduceSpec.get(), CopyOptions.create().setIgnoreNullValue(true));
        BeanUtil.copyProperties(produceDto.getProduce(), targetProduce.get(), CopyOptions.create().setIgnoreNullValue(true));

        produceSpecService.updateById(targetProduceSpec.get());
        produceService.updateById(targetProduce.get());
        return new SuccessTip("修改成功!");
    }

    @ResponseBody
    @PostMapping("updateThumb")
    public Tip updateThumb(String id, String imageUrl) {
        Optional<Produce> target = Optional.ofNullable(produceService.getById(id));
        if (target.isEmpty()) {
            return new ErrorTip(-1, "没有此产品!");
        }

        target.get().setThumb(imageUrl);

        if (produceService.updateById(target.get())) {
            return new SuccessTip("上传成功!");
        } else {
            return new ErrorTip(-1, "上传失败!");
        }
    }

    @ResponseBody
    @PostMapping("delete")
    @Transactional(rollbackFor = Exception.class)
    public Tip delete(Long id) {
        if (produceSpecService.removeById(id)
            && goodsService.deleteByProduceId(id)
            && produceService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
