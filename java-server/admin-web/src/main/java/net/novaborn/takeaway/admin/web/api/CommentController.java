package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.CommentWrapper;
import net.novaborn.takeaway.order.service.impl.CommentService;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/comment")
public class CommentController extends BaseController {
    private UserService userService;

    private CommentService commentService;

    @PostMapping("getCommentListByPage")
    public ResponseEntity<Page> getCommentListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<Long> ids = userService.getByNickName((String) args.get("nickName")).stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            if (ids.size() > 0) {
                args.put("userIds", ids);
            } else {
                args.put("userIds", Arrays.asList("-1"));
            }
        }
        page = (Page) commentService.getCommentListByPage(page, args);
        page.setRecords((List) new CommentWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }
}
