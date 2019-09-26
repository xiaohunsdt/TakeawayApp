package net.novaborn.takeaway.admin.web.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/api/admin/category")
public class CategoryApiController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("getCategoryListByPage")
    public ResponseEntity getCategoryListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page.setOptimizeCountSql(false);
        page = (Page) categoryService.getCategoryListByPage(page, args);
        return ResponseEntity.ok(page);
    }
}
