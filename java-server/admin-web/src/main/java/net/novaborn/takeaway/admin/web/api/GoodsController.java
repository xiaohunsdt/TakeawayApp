package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.web.wrapper.GoodsWrapper;
import net.novaborn.takeaway.admin.web.wrapper.GoodsWrapperEx;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.entity.GoodsStock;
import net.novaborn.takeaway.goods.enums.GoodsState;
import net.novaborn.takeaway.goods.exception.GoodsStockExceptionEnum;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.GoodsStockService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/goods")
public class GoodsController extends BaseController {

    GoodsService goodsService;

    GoodsStockService goodsStockService;

    @ResponseBody
    @GetMapping("getByGoodsId")
    public Object getByGoodsId(String id) {
        Goods goods = goodsService.getById(id);
        return new GoodsWrapperEx(goods).warp();
    }

    @GetMapping("getAllGoods")
    public ResponseEntity getAllGoods() {
        List<Goods> GoodsList = goodsService.list();
        return ResponseEntity.ok(GoodsList);
    }

    @PostMapping("getGoodsListByPage")
    public ResponseEntity getGoodsListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) goodsService.getGoodsListByPage(page, args);
        page.setRecords((List) new GoodsWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @GetMapping("getStockByGoodsId")
    public ResponseEntity getStockByGoodsId(Long id) {
        GoodsStock goodsStock = goodsStockService.getByGoodsId(id)
                .orElseThrow(() -> new SysException(GoodsStockExceptionEnum.STOCK_NOT_FOUND));
        return ResponseEntity.ok(goodsStock.getStock());
    }

    @ResponseBody
    @PostMapping("createNewGoods")
    public Tip createNewGoods(Goods goods) {
        Optional<Goods> tempGoods = goodsService.selectByName(goods.getName());
        if (tempGoods.isPresent()) {
            return new ErrorTip(-1, "存在同名商品!");
        }

        if (goodsService.save(goods) && goodsStockService.createGoodStock(goods, null)) {
            return new SuccessTip("创建成功!");
        } else {
            return new ErrorTip(-1, "创建失败!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("updateGoods")
    public Tip updateGoods(Goods goods, Integer stock) {
        Optional<Goods> targetGoods = Optional.ofNullable(goodsService.getById(goods.getId()));
        if (!targetGoods.isPresent()) {
            return new ErrorTip(-1, "没有此商品名!");
        }

        Optional<Goods> sameNameGoods = goodsService.selectByName(goods.getName());
        if (sameNameGoods.isPresent() && !sameNameGoods.get().getId().equals(goods.getId())) {
            return new ErrorTip(-1, "存在同名商品!");
        }

        if (!goods.getState().equals(GoodsState.ON)) {
            stock = 0;
        }

        if (goods.getState().equals(GoodsState.ON) && stock == 0) {
            stock = -1;
//            return new ErrorTip(-1, "修改失败! 请设置库存后重新!");
        }

        BeanUtil.copyProperties(goods, targetGoods.get(), CopyOptions.create().setIgnoreNullValue(true));
        if (goodsService.updateById(targetGoods.get())) {
            ((GoodsController) AopContext.currentProxy()).updateStock(goods.getId(), stock);
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("updateStock")
    public Tip updateStock(Long goodsId, int stock) {
        Optional<GoodsStock> targetGoodsStock = goodsStockService.getByGoodsId(goodsId);
        if (targetGoodsStock.isEmpty()) {
            return new ErrorTip(-1, "没有此商品的库存信息!");
        }

        targetGoodsStock.get().setStock(stock);
        goodsStockService.updateById(targetGoodsStock.get());

        // 更新商品信息
        Goods goods = goodsService.getById(goodsId);
        if (targetGoodsStock.get().getStock() == 0 || targetGoodsStock.get().getStock() < -1) {
            if (goods.getState().equals(GoodsState.ON)) {
                goods.setState(GoodsState.SHORTAGE);
                goodsService.updateById(goods);
            }
        } else if (targetGoodsStock.get().getStock() == -1 || targetGoodsStock.get().getStock() > 0) {
            if (goods.getState().equals(GoodsState.SHORTAGE)) {
                goods.setState(GoodsState.ON);
                goodsService.updateById(goods);
            }
        }
        return new SuccessTip("修改成功!");
    }

    @ResponseBody
    @PostMapping("updateGoodsThumb")
    public Tip updateGoodsThumb(String id, String imageUrl) {
        Optional<Goods> tempGoods = Optional.ofNullable(goodsService.getById(id));
        if (tempGoods.isEmpty()) {
            return new ErrorTip(-1, "没有此商品名!");
        }

        tempGoods.get().setThumb(imageUrl);

        if (goodsService.updateById(tempGoods.get())) {
            return new SuccessTip("上传成功!");
        } else {
            return new ErrorTip(-1, "上传失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteGoods")
    public Tip deleteGoods(String id) {
        if (goodsService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
