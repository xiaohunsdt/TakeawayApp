package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.CouponTemplateWrapper;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.coupon.entity.CouponTemplate;
import net.novaborn.takeaway.coupon.exception.CouponTemplateExceptionEnum;
import net.novaborn.takeaway.coupon.service.impl.CouponTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/coupon/template")
public class CouponTemplateController extends BaseController {
    private CouponTemplateService couponTemplateService;

    @GetMapping("getTemplateById")
    public ResponseEntity getTemplateById(String id) {
        CouponTemplate couponTemplate = couponTemplateService.getById(id);
        return ResponseEntity.ok(couponTemplate);
    }

    @PostMapping("getTemplateListByPage")
    public ResponseEntity<Page> getTemplateListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) couponTemplateService.getCouponTemplateListByPage(page, args);
        page.setRecords((List)new CouponTemplateWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @GetMapping("getAllTemplateList")
    public ResponseEntity getAllTemplateList() {
        List<CouponTemplate> couponTemplates = couponTemplateService.list();
        return ResponseEntity.ok(couponTemplates);
    }

    @ResponseBody
    @PostMapping("createNewTemplate")
    public Tip createNewTemplate(@Valid CouponTemplate couponTemplate) {
        boolean result;

        if (couponTemplate.getCouponMoney() == 0 && couponTemplate.getCouponDiscount() == 0) {
            throw new SysException(CouponTemplateExceptionEnum.HAVE_NO_MONEY_OR_DISCOUNT);
        }

        if (StrUtil.isBlank(couponTemplate.getId())) {
            result = couponTemplateService.save(couponTemplate);
        } else {
            result = couponTemplateService.updateById(couponTemplate);
        }

        if (result) {
            return new SuccessTip("成功!");
        } else {
            return new ErrorTip(-1, "失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateTemplate")
    public Tip updateTemplate(CouponTemplate couponTemplate) {
        Optional<CouponTemplate> targetTemplate = Optional.ofNullable(couponTemplateService.getById(couponTemplate.getId()));
        if (!targetTemplate.isPresent()) {
            return new ErrorTip(-1, "没有此优惠卷模板!");
        }

        BeanUtil.copyProperties(couponTemplate, targetTemplate.get(), CopyOptions.create().setIgnoreNullValue(true));

        if (couponTemplateService.updateById(targetTemplate.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteTemplate")
    public Tip deleteTemplate(String id) {
        if (couponTemplateService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
