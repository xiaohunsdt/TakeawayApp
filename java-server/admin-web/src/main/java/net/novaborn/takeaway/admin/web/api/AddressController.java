package net.novaborn.takeaway.admin.web.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Setter;
import net.novaborn.takeaway.admin.web.wrapper.AddressWrapper;
import net.novaborn.takeaway.common.exception.SysException;
import net.novaborn.takeaway.common.tips.SuccessTip;
import net.novaborn.takeaway.common.tips.Tip;
import net.novaborn.takeaway.common.utils.CommonUtil;
import net.novaborn.takeaway.user.entity.Address;
import net.novaborn.takeaway.user.entity.User;
import net.novaborn.takeaway.user.exception.AddressExceptionEnum;
import net.novaborn.takeaway.user.service.impl.AddressService;
import net.novaborn.takeaway.user.service.impl.NaverMapService;
import net.novaborn.takeaway.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiaohun
 */
@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping("/api/admin/address")
public class AddressController extends BaseController {
    private UserService userService;

    private AddressService addressService;

    private NaverMapService naverMapService;

    @PostMapping("getAddressListByPage")
    public ResponseEntity<Page> getAddressListByPage(@ModelAttribute Page page, @RequestParam Map<String, Object> args) {
        if (StrUtil.isNotBlank((String) args.get("nickName"))) {
            List<User> userList = userService.getByNickName((String) args.get("nickName"));
            if (userList.size() > 0) {
                args.put("userIds", userList.stream().map(User::getId).collect(Collectors.toList()));
            } else {
                args.put("userIds", "");
            }
        }
        page = (Page) addressService.getAddressListByPage(page, args);
        page.setRecords((List) new AddressWrapper(page.getRecords()).warp());
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @RequestMapping("getAddressById")
    public Object getAddressById(@RequestParam String addressId) {
        Address address = addressService.getById(addressId);
        return ResponseEntity.ok(new AddressWrapper(address).warp());
    }

    @ResponseBody
    @PostMapping("searchAddress")
    public List<Address> searchAddress(String address) {
        return naverMapService.searchAddress(address);
    }

    @ResponseBody
    @PostMapping("updateAddress")
    @Transactional(rollbackFor = RuntimeException.class)
    public Tip updateAddress(@ModelAttribute @Validated Address address) {
        Address target = addressService.getById(address.getId());

        if (address.getPhone() != null && !CommonUtil.validatePhone(address.getPhone())) {
            throw new SysException(AddressExceptionEnum.PHONE_FORMAT_ERROR);
        }

        // 地址发生变化，填入经纬度
//        if (address.getAddress() != null && !target.getAddress().equals(address.getAddress())) {
//            Coordinate coordinate = naverMapService.getGeocode(address.getAddress());
//            address.setX(coordinate.getX());
//            address.setY(coordinate.getY());
//        }
        BeanUtil.copyProperties(address, target, CopyOptions.create().setIgnoreNullValue(true));
        target.updateById();
        return new SuccessTip();
    }

}
