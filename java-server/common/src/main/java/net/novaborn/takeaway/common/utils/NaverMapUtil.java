package net.novaborn.takeaway.common.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.entity.Coordinate;
import net.novaborn.takeaway.common.utils.exception.MapExceptionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用韩国 NAVER MAP 开放提供的 API
 */
public class NaverMapUtil {
    final static private String CLIENT_ID = "jwsa1q8wp1";
    final static private String CLIENT_SECRET = "NTASwddN3FdIAPJW8xVeLTSAQJCebwqBPQ8Cnx8u";

    /**
     * geocode api 地址
     */
    final static private String GEOCODE_API = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";

    @SneakyThrows
    public static Coordinate getGeocode(String address) {
        String result = requestApi(address);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.getString("status");

        if ("OK".equals(status)) {
            JSONArray addresses = jsonObject.getJSONArray("addresses");
            if (addresses.size() == 0) {
                throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR);
            }

            JSONObject addressObject = addresses.getJSONObject(0);
            String roadAddress = addressObject.getString("roadAddress");
            Double x = addressObject.getDouble("x");
            Double y = addressObject.getDouble("y");

            if (!roadAddress.contains("서울")) {
                throw new SysException(MapExceptionEnum.ADDRESS_ERROR);
            }
            Coordinate coordinate = new Coordinate();
            coordinate.setX(x);
            coordinate.setY(y);
            return coordinate;
        } else {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }
    }

    public static List<String> searchAddress(String address) {
        List<String> addresses = new ArrayList<>();

        String result = requestApi(address);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.getString("status");

        if ("OK".equals(status)) {
            JSONArray addresseArr = jsonObject.getJSONArray("addresses");
            if (addresseArr.size() == 0) {
                throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR);
            }

            addresseArr.forEach(item -> {
                String roadAddress = ((JSONObject)item).getString("roadAddress");
                addresses.add(roadAddress);
            });
        } else {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }

        return addresses;
    }

    private static String requestApi(String address) {
        HttpRequest request = HttpUtil.createGet(GEOCODE_API + address);
        generateRequest(request);
        HttpResponse response = request.execute();
        if (response.getStatus() != 200) {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }

        return response.body();
    }

    private static void generateRequest(HttpRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-NCP-APIGW-API-KEY-ID", CLIENT_ID);
        headers.put("X-NCP-APIGW-API-KEY", CLIENT_SECRET);
        request.addHeaders(headers);
    }
}
