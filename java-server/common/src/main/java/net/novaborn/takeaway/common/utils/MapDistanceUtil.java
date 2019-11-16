package net.novaborn.takeaway.common.utils;

/**
 * 根据经纬度算出2个地址之间的距离
 */
public class MapDistanceUtil {
    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据经纬度算出2个地址之间的距离
     * @param lon1 A的经度
     * @param lat1 A的维度
     * @param lon2 B的经度
     * @param lat2 B的维度
     * @return 单位: 米
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }
}
