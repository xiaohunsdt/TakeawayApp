package net.novaborn.takeaway.user.entity;

import lombok.Data;

/**
 * 表示地址的经纬度
 */
@Data
public class Coordinate {
    /**
     * longitude
     */
    private Double x;

    /**
     * latitude
     */
    private Double y;
}
