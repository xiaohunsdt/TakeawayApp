package net.novaborn.takeaway.admin.web.api;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试控制器
 *
 * @author xiaohun
 * @date 2017-08-23 16:02
 */
@Slf4j
@Setter(onMethod_ = {@Autowired})
@Controller
public class TestController extends BaseController{

    @RequestMapping("hello")
    public ResponseEntity hello(@RequestBody Object simpleObject) {
        System.out.println(simpleObject);
        return ResponseEntity.ok("请求成功!");
    }
}
