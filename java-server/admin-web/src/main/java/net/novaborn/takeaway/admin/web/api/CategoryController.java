package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.admin.web.wrapper.CategoryWrapper;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
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
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/category")
public class CategoryController extends BaseController {

    CategoryService categoryService;

    @GetMapping("getAllCategory")
    public ResponseEntity getAllCategory() {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.orderByDesc(Category::getIndex);
        List<Category> categoryList = categoryService.list(query);
        return ResponseEntity.ok(new CategoryWrapper(categoryList).warp());
    }

    @PostMapping("getCategoryListByPage")
    public ResponseEntity getCategoryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) categoryService.getCategoryListByPage(page, args);
        page.setRecords((List) new CategoryWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("createNewCategory")
    public Tip createNewCategory(Category category) {
        Optional<Category> tempCategory = categoryService.getByName(category.getName());
        if (tempCategory.isPresent()) {
            return new ErrorTip(-1, "存在同名分类!");
        }

        if (categoryService.save(category)) {
            return new SuccessTip("创建成功!");
        } else {
            return new ErrorTip(-1, "创建失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateCategory")
    public Tip updateCategory(Category category) {
        Optional<Category> target = Optional.ofNullable(categoryService.getById(category.getId()));
        if (target.isEmpty()) {
            return new ErrorTip(-1, "没有此分类名!");
        }

        BeanUtil.copyProperties(category, target.get(), CopyOptions.create().setIgnoreNullValue(true));
        if (categoryService.updateById(target.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteCategory")
    public Tip deleteCategory(String id) {
        if (categoryService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
