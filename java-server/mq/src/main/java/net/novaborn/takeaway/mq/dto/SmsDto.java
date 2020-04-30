package net.novaborn.takeaway.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SmsDto implements Serializable {
    private String phone;

    private String message;
}
