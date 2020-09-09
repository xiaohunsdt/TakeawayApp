package net.novaborn.takeaway.store.dto;

import lombok.Data;

/**
 * 返回给前台店铺服务状况的dto类
 */

@Data
public class ServiceStateDto {
    protected int state;

    protected String message;

    public ServiceStateDto() { }

    public ServiceStateDto(int state, String message) {
        this.state = state;
        this.message = message;
    }
}
