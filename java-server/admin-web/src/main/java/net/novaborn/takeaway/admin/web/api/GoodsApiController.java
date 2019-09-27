package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
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

@Controller
@RequestMapping("/api/admin/goods")
public class GoodsApiController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("getAllGoods")
    public ResponseEntity getAllGoods() {
        List<Goods> GoodsList = goodsService.list();
        return ResponseEntity.ok(GoodsList);
    }

    @PostMapping("getGoodsListByPage")
    public ResponseEntity getGoodsListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page.setOptimizeCountSql(false);
        page = (Page) goodsService.getGoodsListByPage(page, args);
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
        Optional<Goods> tempGoods = Optional.ofNullable(goodsService.getById(goods.getGoodsId()));
        if (!tempGoods.isPresent()) {
            return new ErrorTip(-1, "没有此商品名!");
        }

        //修改名称
        tempGoods.get().setName(goods.getName());

        if (goodsService.updateById(tempGoods.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
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
