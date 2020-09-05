package net.novaborn.takeaway.address.service;

import net.novaborn.takeaway.admin.AdminApplication;
import net.novaborn.takeaway.user.service.impl.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 3/13/20
 * Time: 5:57 PM
 * Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class AddressServiceTest {
    @Autowired
    AddressService addressService;

    @Test
    public void getDistanceWithStoreTest() {
        System.out.println(addressService.getDistanceWithStore(1111L));
    }
}
