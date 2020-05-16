package net.novaborn.takeaway.banner.entity;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.banner.service.impl.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class BannerTest {

    @Autowired
    BannerService bannerService;

    @Test
    public void createTest() {
        Banner banner = new Banner();
        banner.setTitle("sdfds");
        banner.setImg("asdasdassa");
        banner.insert();
    }

    @Test
    public void updateTest() {
        Banner banner = bannerService.getById("8fcc7d8112e6ec41b164bb1221a76c1c");
        banner.setTitle("cvbcvb");
        banner.updateById();
    }
}