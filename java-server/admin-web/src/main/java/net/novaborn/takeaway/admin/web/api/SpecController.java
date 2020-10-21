package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.goods.entity.Specification;
import net.novaborn.takeaway.goods.service.impl.ProduceSpecService;
import net.novaborn.takeaway.goods.service.impl.SpecificationService;
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
@RequestMapping("/api/admin/spec")
public class SpecController extends BaseController {
    ProduceSpecService produceSpecService;

    SpecificationService specificationService;

    @GetMapping("getAll")
    public ResponseEntity getAll() {
        List<Specification> categoryList = specificationService.list();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("getListByPage")
    public ResponseEntity getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) specificationService.getListByPage(page, args);
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("create")
    public Tip create(Specification specification) {
        Optional<Specification> tempSpecification = specificationService.selectByKey(specification.getKey());
        if (tempSpecification.isPresent()) {
            return new ErrorTip(-1, "存在同名规格!");
        }

        if (specificationService.save(specification)) {
            return new SuccessTip("创建成功!");
        } else {
            return new ErrorTip(-1, "创建失败!");
        }
    }

    @ResponseBody
    @PostMapping("update")
    public Tip update(Specification specification) {
        Optional<Specification> target = Optional.ofNullable(specificationService.getById(specification.getId()));
        if (target.isEmpty()) {
            return new ErrorTip(-1, "没有此分类名!");
        }

        BeanUtil.copyProperties(specification, target.get(), CopyOptions.create().setIgnoreNullValue(true));
        if (specificationService.updateById(target.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("delete")
    public Tip delete(Long id) {
        if (produceSpecService.checkSpecBeUsed(id)) {
            return new ErrorTip(-1, "此规格正在被使用中!请取消使用后再删除!");
        }
        if (specificationService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
