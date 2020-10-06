package net.novaborn.takeaway.user.web.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.user.web.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/user/category")
public class CategoryController extends BaseController {

    CategoryService categoryService;

    @GetMapping("getAllCategory")
    public ResponseEntity getAllCategory() {
        LambdaQueryWrapper<Category> query = Wrappers.lambdaQuery();
        query.orderByDesc(Category::getIndex);
        List<Category> categoryList = categoryService.list(query);
        return ResponseEntity.ok(new CategoryWrapper(categoryList).warp());
    }
}
