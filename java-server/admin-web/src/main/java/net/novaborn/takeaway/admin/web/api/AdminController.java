package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.admin.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.admin.entity.Admin;
import net.novaborn.takeaway.admin.enums.State;
import net.novaborn.takeaway.admin.exception.AdminExceptionEnum;
import net.novaborn.takeaway.admin.service.impl.AdminService;
import net.novaborn.takeaway.admin.web.wrapper.AdminWrapper;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class AdminController extends BaseController {
    private AdminService adminService;

    private JwtTokenUtil jwtTokenUtil;

    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("getAdminInfo")
    @ResponseBody
    public Admin getAdminInfo() {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);
        Optional<Admin> admin = Optional.ofNullable(adminService.getById(adminId));
        admin.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        return admin.get();
    }

    @PostMapping("getAdminInfoById")
    @ResponseBody
    public Admin getAdminInfoById(String adminId) {
        String myId = jwtTokenUtil.getUserIdFromToken(request);
        Optional<Admin> me = Optional.ofNullable(adminService.getById(myId));
        me.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        Optional<Admin> target = Optional.ofNullable(adminService.getById(adminId));
        target.orElseThrow(() -> new SysException(SysExceptionEnum.AUTH_HAVE_NO_USER));

        if (me.get().getLevel().getCode() >= target.get().getLevel().getCode()) {
            throw new SysException(SysExceptionEnum.PERMISSION_DENIED);
        }

        return target.get();
    }

    @PostMapping("getSubAdminByPage")
    @ResponseBody
    public ResponseEntity<Page> getSubAdminByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        args.put("parentId", jwtTokenUtil.getUserIdFromToken(request));
        page = (Page) adminService.getSubAdminListByPage(page, args);
        page.setRecords((List) new AdminWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @PostMapping("createNewSubAdmin")
    @ResponseBody
    public Tip createNewSubAdmin(@Validated Admin admin) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);
        Admin parent = adminService.getById(adminId);

        if (parent.getLevel().getCode() >= admin.getLevel().getCode()) {
            throw new SysException(AdminExceptionEnum.LEVEL_ERROR_SETTING);
        }

        Optional<Admin> temp = adminService.getBaseMapper().selectByName(admin.getUserName());
        temp.ifPresent((target) -> {
            throw new SysException(AdminExceptionEnum.REPEATED_NAME);
        });

        String parentStr = StrUtil.isNotBlank(parent.getParentIds()) ? parent.getParentIds() + "," : "";
        parentStr += adminId;

        admin.setParentIds(parentStr);
        admin.insert();
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("updateAdmin")
    public Tip updateAdmin(@ModelAttribute Admin admin) {
        String adminId = jwtTokenUtil.getUserIdFromToken(request);

        Admin target = adminService.getById(admin.getId());

        if (target.getParentIds() == null || !target.getParentIds().contains(adminId)) {
            throw new SysException(SysExceptionEnum.PERMISSION_DENIED);
        }

        if (!target.getLevel().equals(admin.getLevel())) {
            for (String parentStr : target.getParentIds().split(",")) {
                Admin parent = adminService.getById(parentStr);
                if (parent.getLevel().getCode() >= admin.getLevel().getCode()) {
                    throw new SysException(AdminExceptionEnum.LEVEL_ERROR_SETTING);
                }
            }
        }

        if (admin.getState().equals(State.STOP)) {
            Set<String> keyList = redisTemplate.keys(String.format("jwt:%s:*", admin.getId()));
            keyList.parallelStream().forEach(key -> redisTemplate.delete(key));
        }

        BeanUtil.copyProperties(admin, target, CopyOptions.create().setIgnoreNullValue(true));
        target.updateById();
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("deleteAdmin")
    public Tip deleteAdmin(String id) {
        if (adminService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }

    @PostMapping("logout")
    public ResponseEntity logout() {
        redisTemplate.delete(jwtTokenUtil.getRedisKey(jwtTokenUtil.getToken(request)));
        return ResponseEntity.ok(0);
    }
}
