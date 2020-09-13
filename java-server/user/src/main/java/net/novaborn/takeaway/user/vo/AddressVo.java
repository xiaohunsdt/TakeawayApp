package net.novaborn.takeaway.user.vo;

import lombok.Data;
import net.novaborn.takeaway.user.entity.Address;

/**
 * 扩展的地址数据传输Vo, 韩国 新/老 地址显示
 */
@Data
public class AddressVo extends Address {
    private String jibunAddress;
}
