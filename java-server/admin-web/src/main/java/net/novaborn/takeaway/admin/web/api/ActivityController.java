package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.impl.ActivityService;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
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
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/activity")
public class ActivityController extends BaseController {
    private ActivityService activityService;

    @PostMapping("getActivityListByPage")
    public ResponseEntity<Page> getActivityListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        page = (Page) activityService.getActivityListByPage(page, args);
        return ResponseEntity.ok(page);
    }

    @GetMapping("getAllActivityList")
    public ResponseEntity getAllActivityList() {
        List<Activity> activities = activityService.list();
        return ResponseEntity.ok(activities);
    }

    @ResponseBody
    @PostMapping("createNewActivity")
    public Tip createNewActivity(Activity activity) {
        if (activityService.save(activity)) {
            return new SuccessTip("创建成功!");
        } else {
            return new ErrorTip(-1, "创建失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateActivity")
    public Tip updateActivity(Activity activity) {
        Optional<Activity> targetActivity = Optional.ofNullable(activityService.getById(activity.getId()));
        if (!targetActivity.isPresent()) {
            return new ErrorTip(-1, "没有此活动!");
        }

        BeanUtil.copyProperties(activity, targetActivity.get(), CopyOptions.create().setIgnoreNullValue(true));

        if (activityService.updateById(targetActivity.get())) {
            return new SuccessTip("修改成功!");
        } else {
            return new ErrorTip(-1, "修改失败!");
        }
    }

    @ResponseBody
    @PostMapping("deleteActivity")
    public Tip deleteActivity(String id) {
        if (activityService.removeById(id)) {
            return new SuccessTip("删除成功!");
        } else {
            return new ErrorTip(-1, "删除失败!");
        }
    }
}
