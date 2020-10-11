package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.web.dto.ProduceDto;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.exception.GoodsStockExceptionEnum;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/produce")
public class ProduceController extends BaseController {

    ProduceService produceService;

    GoodsStockService goodsStockService;

    @ResponseBody
    @GetMapping("getById")
    public Object getById(String id) {
        Produce produce = produceService.getById(id);
        return produce;
    }

    @GetMapping("getAll")
    public ResponseEntity getAll() {
        List<Produce> produceList = produceService.list();
        return ResponseEntity.ok(produceList);
    }

    @PostMapping("getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) produceService.getListByPage(page, args);
//        page.setRecords((List) new GoodsWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("create")
    @Transactional(rollbackFor = Exception.class)
    public Tip create(@RequestBody @Validated ProduceDto produceDto) {
        Optional<Produce> tempGoods = produceService.selectByName(produceDto.getProduce().getName());
        if (tempGoods.isPresent()) {
            return new ErrorTip(-1, "存在同名商品!");
        }

        produceService.save(produceDto.getProduce());
        return null;
    }

    @ResponseBody
    @PostMapping("update")
    @Transactional(rollbackFor = Exception.class)
    public Tip update(@RequestBody @Validated ProduceDto produceDto) {
//        Optional<Goods> targetGoods = Optional.ofNullable(goodsService.getById(goods.getId()));
//        if (targetGoods.isEmpty()) {
//            return new ErrorTip(-1, "没有此商品名!");
//        }
//
//        Optional<Goods> sameNameGoods = goodsService.selectByName(goods.getName());
//        if (sameNameGoods.isPresent() && !sameNameGoods.get().getId().equals(goods.getId())) {
//            return new ErrorTip(-1, "存在同名商品!");
//        }
//
//        if (!goods.getState().equals(ProduceState.ON)) {
//            stock = 0;
//        }
//
//        if (goods.getState().equals(ProduceState.ON) && stock == 0) {
//            stock = -1;
////            return new ErrorTip(-1, "修改失败! 请设置库存后重新!");
//        }
//
//        BeanUtil.copyProperties(goods, targetGoods.get(), CopyOptions.create().setIgnoreNullValue(true));
//        if (goodsService.updateById(targetGoods.get())) {
//            ((ProduceController) AopContext.currentProxy()).updateStock(goods.getId(), stock);
//            return new SuccessTip("修改成功!");
//        } else {
//            return new ErrorTip(-1, "修改失败!");
//        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("updateStock")
    public Tip updateStock(Long goodsId, int stock) {
//        Optional<GoodsStock> targetGoodsStock = goodsStockService.getByGoodsId(goodsId);
//        if (targetGoodsStock.isEmpty()) {
//            return new ErrorTip(-1, "没有此商品的库存信息!");
//        }
//
//        targetGoodsStock.get().setStock(stock);
//        goodsStockService.updateById(targetGoodsStock.get());
//
//        // 更新商品信息
//        Goods goods = goodsService.getById(goodsId);
//        if (targetGoodsStock.get().getStock() == 0 || targetGoodsStock.get().getStock() < -1) {
//            if (goods.getState().equals(GoodsState.ON)) {
//                goods.setState(GoodsState.SHORTAGE);
//                goodsService.updateById(goods);
//            }
//        } else if (targetGoodsStock.get().getStock() == -1 || targetGoodsStock.get().getStock() > 0) {
//            if (goods.getState().equals(GoodsState.SHORTAGE)) {
//                goods.setState(GoodsState.ON);
//                goodsService.updateById(goods);
//            }
//        }
        return new SuccessTip("修改成功!");
    }

    @ResponseBody
    @PostMapping("updateGoodsThumb")
    public Tip updateGoodsThumb(String id, String imageUrl) {
//        Optional<Produce> target = Optional.ofNullable(produceService.getById(id));
//        if (target.isEmpty()) {
//            return new ErrorTip(-1, "没有此商品名!");
//        }
//
//        target.get().setThumb(imageUrl);
//
//        if (produceService.updateById(target.get())) {
//            return new SuccessTip("上传成功!");
//        } else {
//            return new ErrorTip(-1, "上传失败!");
//        }
        return null;
    }

    @ResponseBody
    @PostMapping("delete")
    public Tip delete(String id) {
//        if (goodsService.removeById(id)) {
//            return new SuccessTip("删除成功!");
//        } else {
//            return new ErrorTip(-1, "删除失败!");
//        }
        return null;
    }
}
