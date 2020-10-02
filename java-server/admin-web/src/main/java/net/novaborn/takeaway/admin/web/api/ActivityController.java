package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.impl.ActivityService;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.exception.SysExceptionEnum;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/activity")
public class ActivityController extends BaseController {
    private ActivityService activityService;

    @GetMapping("getActivityById")
    public ResponseEntity getActivityById(String id) {
        Activity activity = activityService.getById(id);
        return ResponseEntity.ok(activity);
    }

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
    public Tip createNewActivity(@Valid Activity activity) {
        boolean result;

        result = activity.getId() == null ? activityService.save(activity) : activityService.updateById(activity);

        if (result) {
            return new SuccessTip("成功!");
        } else {
            return new ErrorTip(-1, "失败!");
        }
    }

    @ResponseBody
    @PostMapping("updateActivity")
    public Tip updateActivity(Activity activity) {
        Optional<Activity> targetActivity = Optional.ofNullable(activityService.getById(activity.getId()));
        if (targetActivity.isEmpty()) {
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
    @PostMapping("changeIsShow")
    public Tip changeIsShow(@RequestParam String id, @RequestParam Boolean isShow) {
        Optional<Activity> targetActivity = Optional.ofNullable(activityService.getById(id));
        if (targetActivity.isEmpty()) {
            return new ErrorTip(-1, "没有此活动!");
        }

        targetActivity.get().setIsShow(isShow);

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

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Tip uploadImg(@RequestParam MultipartFile file) {
        String imgName;

        if (!file.isEmpty()) {
            try {
                String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                imgName = IdUtil.fastSimpleUUID() + prefix;
                // 文件保存路径
//                String filePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + "/upload/leaguePics/";
                String filePath = new File(System.getProperty("user.dir")) + "/upload/images/activity/";

                // 转存文件
                File dir = new File(filePath);
                if (!dir.exists() && !dir.isDirectory()) {
                    dir.mkdirs();
                }

                File target = new File(filePath + imgName);

                //保存图片到本地
                file.transferTo(target);

                //图片压缩
                ImgUtil.compress(target, target, 0.1f);
            } catch (Exception e) {
                log.error(null, e);
                throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
            }
        } else {
            throw new SysException(SysExceptionEnum.UPLOAD_IMAGE_FAILED);
        }
        return new SuccessTip(imgName);
    }
}
