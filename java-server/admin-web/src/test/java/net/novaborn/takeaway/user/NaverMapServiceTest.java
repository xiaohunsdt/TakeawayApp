package net.novaborn.takeaway.user;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.user.service.impl.NaverMapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdminApplication.class})
public class NaverMapServiceTest {

    @Autowired
    NaverMapService naverMapService;

    @Test
    public void getGeocodeTest() {
        naverMapService.getGeocode("이화여자대학교포스코관");
    }

    @Test
    public void searchAddressTest() {
        System.out.println(naverMapService.searchAddress("신촌로 150"));
    }

}