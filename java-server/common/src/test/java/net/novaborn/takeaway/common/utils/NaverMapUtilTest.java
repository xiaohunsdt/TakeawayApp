package net.novaborn.takeaway.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class NaverMapUtilTest {

    @Test
    public void getGeocode() {
        NaverMapUtil.getGeocode("서울 마포구 신촌로 150");
    }
}