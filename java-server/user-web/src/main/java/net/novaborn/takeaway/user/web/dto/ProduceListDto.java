package net.novaborn.takeaway.user.web.dto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Data;
import net.novaborn.takeaway.category.entity.Category;
import net.novaborn.takeaway.category.service.impl.CategoryService;
import net.novaborn.takeaway.common.SpringContextHolder;
import net.novaborn.takeaway.goods.entity.Produce;
import net.novaborn.takeaway.user.web.wrapper.ProduceWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ProduceListDto {
    private List<CategoryDto> categoryGoods = new ArrayList<>();

    public ProduceListDto(List<Produce> produceList) {
        CategoryService categoryService = SpringContextHolder.getBean(CategoryService.class);

        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Category::getId, produceList.stream().map(Produce::getCategoryId).distinct().collect(Collectors.toList()));

        categoryService.list(queryWrapper).stream()
            .sorted(Comparator.comparing(Category::getIndex).reversed().thenComparing(Category::getCreateDate))
            .forEach(category -> {
                List<Map> goodsWrapperExList = produceList.parallelStream()
                    .filter(produce -> produce.getCategoryId().equals(category.getId()))
                    .map(goods -> (Map) new ProduceWrapper(goods, category).warp())
                    .collect(Collectors.toList());

                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                categoryDto.setParentId(category.getParentId());
                categoryDto.setProduceList(goodsWrapperExList);
                categoryGoods.add(categoryDto);
            });
    }

    @Data
    private class CategoryDto {
        private Long id;
        private String name;
        private Long parentId;
        private List<Map> produceList;
    }
}
