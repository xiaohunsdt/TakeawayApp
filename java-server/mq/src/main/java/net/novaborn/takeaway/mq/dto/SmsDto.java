package net.novaborn.takeaway.mq.dto;

import lombok.Data;

@Data
public class SmsDto {
    private String phone;

    private String message;
}
