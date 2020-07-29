package net.novaborn.takeaway.user.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.exception.MapExceptionEnum;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface INaverMapService {
    String CLIENT_ID = "jwsa1q8wp1";
    String CLIENT_SECRET = "NTASwddN3FdIAPJW8xVeLTSAQJCebwqBPQ8Cnx8u";

    /**
     * geocode api 地址
     */
    String GEOCODE_API = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";

    /**
     * html5 扩展获取地址和经纬度
     */
    String HTML_API = "https://m.map.naver.com/search2/searchMore.nhn?sm=clk&style=v5&page=1&displayCount=75&type=SITE_1&query=";

    /**
     * 获取经纬度
     * @param address 被指定的地址
     * @return
     */
    Coordinate getGeocode(String address);

    /**
     * 搜索地址列表
     * @param address 被指定的地址
     * @return
     */
    List<Address> searchAddress(String address);

    /**
     * 搜索地址列表(扩展)
     * 注: 此方法不使用open api
     * @param address
     * @return
     */
    List<Address> searchAddressEx(String address);

    default String requestApi(String address) {
        HttpRequest request = HttpUtil.createGet(GEOCODE_API + address);
        generateRequest(request);
        HttpResponse response = request.execute();
        if (response.getStatus() != 200) {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }

        return response.body();
    }

    default void generateRequest(HttpRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
        headers.put("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
        request.addHeaders(headers);
    }
}
