package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
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

@Controller
@RequestMapping("/api/admin/category")
public class CategoryApiController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("getAllCategory")
    public ResponseEntity getAllCategory() {
        List<Category> categoryList = categoryService.list();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("getCategoryListByPage")
    public ResponseEntity getCategoryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page.setOptimizeCountSql(false);
        page = (Page) categoryService.getCategoryListByPage(page, args);
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("createNewCategory")
    public Tip createNewCategory(Category category) {
        Optional<Category> tempCategory = categoryService.selectByName(category.getName());
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
        Optional<Category> tempCategory = Optional.ofNullable(categoryService.getById(category.getId()));
        if (!tempCategory.isPresent()) {
            return new ErrorTip(-1, "没有此分类名!");
        }

        //修改名称
        tempCategory.get().setName(category.getName());

        if (categoryService.updateById(tempCategory.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("delteCategory")
    public Tip delteCategory(String id) {
        if (categoryService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
