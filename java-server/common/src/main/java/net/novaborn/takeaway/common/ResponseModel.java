package net.novaborn.takeaway.common;

import lombok.Data;

@Data
public class ResponseModel {
    int code;
    Object data;

    public ResponseModel(Object data) {
        this.code = 0;
        this.data = data;
    }
}
