package net.novaborn.takeaway.user.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import lombok.Setter;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.ErrorTip;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.common.utils.CommonUtil;
import net.novaborn.takeaway.user.common.auth.util.JwtTokenUtil;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.Coordinate;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.exception.AddressExceptionEnum;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.NaverMapService;
import net.novaborn.takeaway.user.service.impl.UserService;
import net.novaborn.takeaway.user.vo.AddressVo;
import net.novaborn.takeaway.user.web.wrapper.AddressWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/user/address")
public class AddressController extends BaseController {
    private UserService userService;

    private AddressService addressService;

    private NaverMapService naverMapService;

    private JwtTokenUtil jwtTokenUtil;

    @ResponseBody
    @GetMapping("getAddressById")
    public Object getAddressById(@RequestParam Long addressId) {
        Address address = addressService.getById(addressId);
        return ResponseEntity.ok(new AddressWrapper(address).warp());
    }

    @ResponseBody
    @GetMapping("getMyAddressList")
    public Object getMyAddressList() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        List<Address> addressList = addressService.selectByUserId(user.get().getId());

        return ResponseEntity.ok(new AddressWrapper(addressList).warp());
    }

    @ResponseBody
    @GetMapping("getDefaultAddress")
    public Object getAllCategory() {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);

        Optional<Address> address = addressService.selectDefaultAddressByUserId(user.get().getId());

        if (address.isPresent()) {
            return ResponseEntity.ok(new AddressWrapper(address.get()).warp());
        } else {
            return ResponseEntity.ok();
        }
    }

    @ResponseBody
    @PostMapping("searchAddress")
    public List<AddressVo> searchAddress(String address) {
        return naverMapService.searchAddress(address);
    }

    @ResponseBody
    @GetMapping("getAddressStaticMap")
    public Tip getAddressStaticMap(Address address) {
        return new SuccessTip(Base64.encode(naverMapService.getAddressStaticMap(address)));
    }

    @ResponseBody
    @PostMapping("createNewAddress")
    public Tip createNewAddress(@ModelAttribute @Validated Address address) {
        String openId = jwtTokenUtil.getUsernameFromToken(request);
        Optional<User> user = userService.selectByOpenId(openId);
        Optional<Address> defaultAddress = addressService.selectDefaultAddressByUserId(user.get().getId());

        // 清除空字符
        address.setAddress(address.getAddress().trim());
        address.setDetail(address.getDetail().trim());
        address.setPhone(address.getPhone().trim());

        address.setUserId(user.get().getId());

        if (!CommonUtil.validateKoreaPhone(address.getPhone())) {
            throw new SysException(AddressExceptionEnum.PHONE_FORMAT_ERROR);
        }

        // 填入经纬度
        if (StrUtil.isNotBlank(address.getAddress())
                && (address.getX() == null || address.getY() == null)) {
            Coordinate coordinate = naverMapService.getGeocode(address.getAddress());
            address.setX(coordinate.getX());
            address.setY(coordinate.getY());
        }

        if (defaultAddress.isEmpty()) {
            address.setIsDefault(true);
        }

        if (address.insert()) {
            return new SuccessTip();
        } else {
            return new ErrorTip(-1, "添加新地址失败!请联系客服");
        }
    }

    @ResponseBody
    @PostMapping("updateAddress")
    @Transactional(rollbackFor = RuntimeException.class)
    public Tip updateAddress(@ModelAttribute @Validated Address address) {
        Address target = addressService.getById(address.getId());

        // 清除空字符
        address.setAddress(address.getAddress().trim());
        address.setDetail(address.getDetail().trim());
        address.setPhone(address.getPhone().trim());

        if (!CommonUtil.validateKoreaPhone(address.getPhone())) {
            throw new SysException(AddressExceptionEnum.PHONE_FORMAT_ERROR);
        }

        //如果这个地址是默认地址，需要做进一步的默认地址唯一处理
        if (address.getIsDefault() != null && address.getIsDefault()) {
            addressService.setDefaultAddress(target.getId(), target.getUserId());
        }

        // 地址发生变化，填入经纬度
        if (StrUtil.isNotBlank(address.getAddress())
                && (address.getX() == null || address.getY() == null)) {
            Coordinate coordinate = naverMapService.getGeocode(address.getAddress());
            address.setX(coordinate.getX());
            address.setY(coordinate.getY());
        }
        BeanUtil.copyProperties(address, target, CopyOptions.create().setIgnoreNullValue(true));
        addressService.updateById(target);
        return new SuccessTip();
    }

    @ResponseBody
    @PostMapping("setDefaultAddress")
    public Tip setDefaultAddress(Long id) {
        Address target = addressService.getById(id);
        return addressService.setDefaultAddress(id, target.getUserId())
                ? new SuccessTip() : new ErrorTip(-1, "设置失败!");
    }

    @ResponseBody
    @PostMapping("deleteAddress")
    public Tip deleteAddress(@RequestParam Long addressId) {
        addressService.removeById(addressId);
        return new SuccessTip();
    }
}
