package net.novaborn.takeaway.print;

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
public class PrintApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrintApplication.class, args);
        log.info("Application is success!");
    }
}
