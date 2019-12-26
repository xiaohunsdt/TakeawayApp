package net.novaborn.takeaway.user.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.impl.ActivityService;
import net.novaborn.takeaway.user.web.wrapper.ActivityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Slf4j
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/activity")
public class ActivityController extends BaseController {
    private ActivityService activityService;

    @GetMapping("getActivityById")
    public ResponseEntity getActivityById(String id) {
        Activity activity = activityService.getById(id);
        return ResponseEntity.ok(new ActivityWrapper(activity).warp());
    }

    @GetMapping("getAllActivityList")
    public ResponseEntity getAllActivityList() {
        List<Activity> activities = activityService.list()
                .stream()
                .filter(Activity::getIsShow)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ActivityWrapper(activities).warp());
    }
}
