package net.novaborn.takeaway.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

/**
 * @author xiaohun
 * @since 2019-09-20
 */
@Data
@TableName("`address`")
public class Address extends Model<Address> {
    private static final long serialVersionUID = -3379009665983595457L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String userId;

    /**
     * 具体地址
     */
    @NotBlank(message = "地址不能为空!!")
    private String address;

    /**
     * 地址详细说明
     */
    private String detail;

    /**
     * longitude 经度
     */
    private Double x;

    /**
     * latitude 纬度
     */
    private Double y;

    @NotBlank(message = "手机号不能为空!!")
    private String phone;

    private Boolean isDefault;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @JSONField(serialize = false)
    @TableLogic
    private Integer deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) &&
                Objects.equals(userId, address1.userId) &&
                Objects.equals(address, address1.address) &&
                Objects.equals(detail, address1.detail) &&
                Objects.equals(phone, address1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, address, detail, phone);
    }
}