package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import net.novaborn.takeaway.activity.entity.Activity;
import net.novaborn.takeaway.activity.service.impl.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/activity")
public class ActivityController extends BaseController {
    private ActivityService activityService;

    @GetMapping("getAllActivityList")
    public ResponseEntity getAllActivityList() {
        List<Activity> activities = activityService.list();
        return ResponseEntity.ok(activities);
    }
}
