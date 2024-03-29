package net.novaborn.takeaway.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 * @author xiaohun
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"net.novaborn.takeaway"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        log.info("Application is success!");
    }
}
