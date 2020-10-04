package net.novaborn.takeaway.user.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 表示地址的经纬度
 */
@Data
public class Coordinate {
    /**
     * longitude
     */
    @NotNull(message = "经度不能为空")
    private Double x;

    /**
     * latitude
     */
    @NotNull(message = "纬度不能为空")
    private Double y;
}
