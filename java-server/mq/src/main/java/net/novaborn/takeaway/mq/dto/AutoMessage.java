package net.novaborn.takeaway.mq.dto;

import lombok.Data;

import java.util.List;

@Data
public class AutoMessage {
    private String message;

    private List<String> imgUrlList;
}
