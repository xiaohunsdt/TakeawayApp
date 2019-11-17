package net.novaborn.takeaway.user.web.dto;

import lombok.Data;

/**
 * 返回给前台各种服务状况的dto类
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
