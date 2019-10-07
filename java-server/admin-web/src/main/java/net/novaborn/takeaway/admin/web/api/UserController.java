package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
@RequestMapping("/api/admin/user")
public class UserController extends BaseController {

    private UserService userService;

    @PostMapping("getUserListByPage")
    public ResponseEntity getUserListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page.setOptimizeCountSql(false);
        page = (Page) userService.getUserListByPage(page, args);
//        page.setRecords((List) new CategoryWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @PostMapping("updateUser")
    public Tip updateUser(User user) {
        Optional<User> tempUser = Optional.ofNullable(userService.getById(user.getId()));
        if (!tempUser.isPresent()) {
            return new ErrorTip(-1, "没有此用户!");
        }

        BeanUtil.copyProperties(user, tempUser.get(), CopyOptions.create().setIgnoreNullValue(true));

        if (userService.updateById(tempUser.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteUser")
    public Tip deleteUser(String id) {
        if (userService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }

}
