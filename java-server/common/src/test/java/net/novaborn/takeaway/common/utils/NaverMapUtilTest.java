package net.novaborn.takeaway.common.utils;

import org.junit.Test;

public class NaverMapUtilTest {

    @Test
    public void getGeocodeTest() {
        NaverMapUtil.getGeocode("이화여자대학교포스코관");
    }

    @Test
    public void searchAddressTest() {
        System.out.println(NaverMapUtil.searchAddress("신촌로 150"));
    }

}