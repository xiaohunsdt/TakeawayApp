package net.novaborn.takeaway.admin.entity;

import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void createAdminTest(){
        Category category = new Category();
        category.setName("素菜小炒");
        categoryService.save(category);
    }

    @Test
    public void updateAdminTest(){
        Category category = categoryService.getById("8410fe3eac3dd72c7b0aeb4f24cc05a8");
        category.setName("素菜小炒");
        category.updateById();
    }
}