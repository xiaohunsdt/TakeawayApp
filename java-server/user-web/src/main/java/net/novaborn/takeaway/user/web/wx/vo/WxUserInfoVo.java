package net.novaborn.takeaway.user.web.wx.vo;

import lombok.Data;

/**
 * 微信 UserInfo 数据结构
 * @author xiaohun
 */
@Data
public class WxUserInfoVo {
    private String nickName;
    private Integer gender;
    private String avatarUrl;
}
