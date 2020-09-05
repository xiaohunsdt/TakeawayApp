package net.novaborn.takeaway.user.web.dto;

import lombok.Data;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.user.web.wrapper.GoodsWrapperEx;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class GoodsListDto {
    private List<CategoryDto> categoryGoods = new ArrayList<>();

    public GoodsListDto(List<Goods> goodsList) {
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);
        categoryService.list().stream()
                .sorted(Comparator.comparing(Category::getCreateDate))
                .forEach(category -> {
                    List<Map> goodsWrapperExList = goodsList.parallelStream()
                            .filter(goods -> goods.getCategoryId().equals(category.getId()))
                            .map(goods -> (Map) new GoodsWrapperEx(goods, category).warp())
                            .collect(Collectors.toList());

                    CategoryDto categoryDto = new CategoryDto();
                    categoryDto.setId(category.getId());
                    categoryDto.setName(category.getName());
                    categoryDto.setParentId(category.getParentId());
                    categoryDto.setGoodsList(goodsWrapperExList);
                    categoryGoods.add(categoryDto);
                });
    }

    @Data
    private class CategoryDto {
        private Long id;
        private String name;
        private Long parentId;
        private List<Map> goodsList;
    }
}
