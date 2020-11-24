package net.novaborn.takeaway.category.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.admin.web.api.CategoryController;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.goods.service.impl.GoodsService;
import net.novaborn.takeaway.goods.service.impl.ProduceService;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class CategoryTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryController categoryController;

    @Test
    public void createTest() {
        Category category = new Category();
        category.setName("素菜小炒");
        categoryService.save(category);
    }

    @Test
    public void updateTest() {
        Category category = categoryService.getById("8410fe3eac3dd72c7b0aeb4f24cc05a8");
        category.setName("素菜小炒");
        categoryService.updateById(category);
    }

    @Test
    public void deleteTest() {
/*        1331068515361349633	主页推荐菜		0	1322461044052975617	2020-11-24 11:53:58	2020-11-25 02:03:41	1
        1322466758834118657	早餐粥类		90	1322461044052975617	2020-10-31 18:13:40	2020-11-25 02:03:39	1
        1325842561529667586	素菜		0	1323598771356033026	2020-11-10 01:47:54	2020-11-25 02:03:38	1
        1325847908516487170	工作餐盒饭		0	1323598771356033026	2020-11-10 02:09:09	2020-11-25 02:03:37	1
        1321361622783090689	素菜小菜		0	1321354959531401218	2020-10-28 17:02:15	2020-11-25 02:03:35	1
        1330618571164872705	麻辣烫		0	1323598771356033026	2020-11-23 06:06:03	2020-11-23 06:07:16	1

 */
        categoryController.deleteCategory(1331068515361349633L);
        categoryController.deleteCategory(1322466758834118657L);
        categoryController.deleteCategory(1325842561529667586L);
        categoryController.deleteCategory(1325847908516487170L);
        categoryController.deleteCategory(1321361622783090689L);
        categoryController.deleteCategory(1330618571164872705L);
    }
}