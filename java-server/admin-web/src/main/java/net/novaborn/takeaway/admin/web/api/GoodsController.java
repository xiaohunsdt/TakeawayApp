package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.web.wrapper.GoodsWrapper;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("getByGoodsId")
    public ResponseEntity getByGoodsId(String id) {
        Goods goods = goodsService.getById(id);
        return ResponseEntity.ok(goods);
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

    @ResponseBody
    @PostMapping("createNewGoods")
    public Tip createNewGoods(Goods goods) {
        Optional<Goods> tempGoods = goodsService.selectByName(goods.getName());
        if (tempGoods.isPresent()) {
            return new ErrorTip(-1, "存在同名商品!");
        }

        if (goodsService.save(goods)) {
            return new SuccessTip("创建成功!");
        } else {
            return new ErrorTip(-1, "创建失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateGoods")
    public Tip updateGoods(Goods goods) {
        Optional<Goods> targetGoods = Optional.ofNullable(goodsService.getById(goods.getId()));
        if (!targetGoods.isPresent()) {
            return new ErrorTip(-1, "没有此商品名!");
        }

        Optional<Goods> sameNameGoods = goodsService.selectByName(goods.getName());
        if (sameNameGoods.isPresent() && !sameNameGoods.get().getId().equals(goods.getId())) {
            return new ErrorTip(-1, "存在同名商品!");
        }

        BeanUtil.copyProperties(goods, targetGoods.get(), CopyOptions.create().setIgnoreNullValue(true));
        if (goodsService.updateById(targetGoods.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateGoodsThumb")
    public Tip updateGoodsThumb(String id, String imageUrl) {
        Optional<Goods> tempGoods = Optional.ofNullable(goodsService.getById(id));
        if (!tempGoods.isPresent()) {
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
    @PostMapping("delteGoods")
    public Tip delteGoods(String id) {
        if (goodsService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
