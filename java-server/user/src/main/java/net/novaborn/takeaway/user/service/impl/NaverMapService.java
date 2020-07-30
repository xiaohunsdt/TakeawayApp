package net.novaborn.takeaway.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.utils.exception.MapExceptionEnum;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.Coordinate;
import net.novaborn.takeaway.user.service.INaverMapService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 调用韩国 NAVER MAP 提供的 OPEN API
 */
@Slf4j
@Service
public class NaverMapService implements INaverMapService {

    @SneakyThrows
    public Coordinate getGeocode(String address) {
        String result = requestApi(address);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.getString("status");

        if ("OK".equals(status)) {
            JSONArray addresses = jsonObject.getJSONArray("addresses");
            if (addresses.size() == 0) {
                throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR, address);
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

    public List<Address> searchAddress(String address) {
        List<Address> addresses = new ArrayList<>();

        String result = requestApi(address);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.getString("status");

        if ("OK".equals(status)) {
            JSONArray addressesArr = jsonObject.getJSONArray("addresses");
            if (addressesArr.size() == 0) {
//                throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR);
                return this.searchAddressEx(address);
            }

            addressesArr.forEach(item -> {
                String roadAddress = ((JSONObject) item).getString("roadAddress");
                Double x = ((JSONObject) item).getDouble("x");
                Double y = ((JSONObject) item).getDouble("y");
                Address temp = new Address();
                temp.setAddress(roadAddress);
                temp.setX(x);
                temp.setY(y);
                addresses.add(temp);
            });
        } else {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }

        return addresses;
    }

    @Override
    public List<Address> searchAddressEx(String address) {
        List<Address> addressList = new ArrayList<>();
        HttpRequest request = HttpUtil.createGet(HTML_API + address);
        generateRequest(request);
        HttpResponse response = request.execute();
        if (response.getStatus() != 200) {
            throw new SysException(MapExceptionEnum.REQUEST_API_ERROR);
        }
        String result = response.body();

        if (StrUtil.isBlank(result) || !result.contains("\"result\"")) {
            throw new SysException(MapExceptionEnum.REQUEST_ERROR);
        }

        JSONObject jsonObject = JSON.parseObject(result).getJSONObject("result").getJSONObject("site");
        if (jsonObject == null) {
            throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR, address);
        }

        JSONArray addressesArr = jsonObject.getJSONArray("list");
        if (addressesArr.size() == 0) {
            throw new SysException(MapExceptionEnum.NO_ADDRESS_ERROR, address);
        }

        addressesArr.forEach(item -> {

        });

        for(Object item : addressesArr.stream().toArray()){
            String name = ((JSONObject) item).getString("name");
            String roadAddress = ((JSONObject) item).getString("roadAddress");
            Double x = ((JSONObject) item).getDouble("x");
            Double y = ((JSONObject) item).getDouble("y");
            Address temp = new Address();
            temp.setAddress(String.format("%s %s", roadAddress, name));
            temp.setX(x);
            temp.setY(y);
            addressList.add(temp);
        }

        //过滤掉重复的地址
        addressList = addressList.stream().distinct().collect(Collectors.toList());
        return addressList;
    }
}
