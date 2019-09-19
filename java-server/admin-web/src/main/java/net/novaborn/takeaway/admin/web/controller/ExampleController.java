package net.novaborn.takeaway.activity.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/api/admin/")
public class ExampleController {

    @RequestMapping("hello")
    public ResponseEntity hello(@RequestBody Object simpleObject) {
        System.out.println(simpleObject);
        return ResponseEntity.ok("请求成功!");
    }
}
