package net.novaborn.wechat.auto.entity;

import java.util.List;

public class AutoMessage {
    private String message;
    private List<String> imgUrlList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }
}
