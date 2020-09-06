package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.enums.Level;
import net.novaborn.takeaway.admin.exception.AdminExceptionEnum;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.store.entity.Store;
import net.novaborn.takeaway.store.enums.PaymentWay;
import net.novaborn.takeaway.store.service.impl.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/store")
public class StoreController extends BaseController {
    private AdminService adminService;

    private StoreService storeService;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("getStoreById")
    @ResponseBody
    public Store getStoreById(String storeId) {
        return storeService.getById(storeId);
    }

    @PostMapping("getListByPage")
    @ResponseBody
    public ResponseEntity<Page> getListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) storeService.getListByPage(page, args);
//        page.setRecords((List) new AdminWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @PostMapping("create")
    @ResponseBody
    public Tip create(@Validated Store store) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);
        Admin admin = adminService.getById(adminId);

        if (!admin.getLevel().equals(Level.SUPER_MANAGER)) {
            throw new SysException(AdminExceptionEnum.LEVEL_ERROR);
        }

        store.insert();
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("update")
    public Tip update(@ModelAttribute Store store) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);
        Admin admin = adminService.getById(adminId);

        if (!admin.getLevel().equals(Level.SUPER_MANAGER)) {
            throw new SysException(AdminExceptionEnum.LEVEL_ERROR);
        }

        Store target = storeService.getById(store.getId());
        BeanUtil.copyProperties(store, target, CopyOptions.create().setIgnoreNullValue(true));

        target.updateById();
        return new SuccessTip();
    }
}
